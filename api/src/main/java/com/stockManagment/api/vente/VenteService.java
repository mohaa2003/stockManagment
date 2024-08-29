package com.stockManagment.api.vente;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.client.Client;
import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.client.ClientRepo;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteRepo;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteDto;
import com.stockManagment.api.dette.DetteRepo;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
import com.stockManagment.api.ligneVente.LigneVenteDto;
import com.stockManagment.api.ligneVente.LigneVenteRepo;
import com.stockManagment.api.produit.Produit;
import com.stockManagment.api.produit.ProduitDto;
import com.stockManagment.api.produit.ProduitRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class VenteService {

    private final VenteRepo venteRepo;
    private final LigneVenteRepo ligneVenteRepo;
    private final CompteRepo compteRepo;
    private final ClientRepo clientRepo;
    private final DetteRepo detteRepo;
    private final ProduitRepo produitRepo;

    @Transactional
    public Integer save(VenteDto vente) {
        //Implementing update logique (test)
        if(venteRepo.existsById(vente.getId())){
            VenteDto currentVente = VenteDto.fromEntity(venteRepo.findById(vente.getId()).get());

            //verification de la suffisance de stock !

            List<LigneVenteDto> listeDesVentes = currentVente.getLigneVenteList();
            if(listeDesVentes == null || listeDesVentes.isEmpty()){
                throw new IllegalArgumentException("Error ! existing of a selling without any lines is impossible ");
            }
            listeDesVentes.stream().forEach((LigneVenteDto ligneVenteDto)-> {
                Optional<Produit> produit = produitRepo.findById(ligneVenteDto.getProduit().getId());
                if(produit.isEmpty()){
                    log.warn("Error! Product missing in the line");
                    throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+ligneVenteDto.getProduit().getId(),ErrorCodes.PRODUIT_NOT_FOUND);
                }
                ProduitDto produitDto = ligneVenteDto.getProduit();
                Integer produitQtt = produitDto.getQuantiteDisponible();
                produitDto.setQuantiteDisponible(produitQtt + ligneVenteDto.getQuantite());
                produitRepo.save(ProduitDto.toEntity(produitDto));

                ligneVenteRepo.deleteById(ligneVenteDto.getId());
            });

            //traitement de compte
            Optional<Compte> compte = compteRepo.findById(currentVente.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Compte missing in the current vente !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentVente.getCompte();
            Double balance = currentCompte.getCredit();
            if(currentVente.getSomePaye()>balance){
                log.warn("Balance insuffisante when restoring vente informations");
                throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
            }
            currentCompte.setCredit(balance - currentVente.getSomePaye()) ;
            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de client et des dettes
            if(currentVente.getPrixTotaleVente() < currentVente.getPrixApresRemise()){
                log.warn("Error ! prixVenteTotal should be greater than prixApresRemise");
                throw new IllegalArgumentException("prixVenteTotal should be greater than prixApresRemise");
            }
            //typically i should make a verification if prix is less than the prix paye but i think about if he pays more than the buying price and mkach sarf !

            Double dette = currentVente.getPrixApresRemise() - currentVente.getSomePaye();
            Optional<Client> client = clientRepo.findById(currentVente.getClient().getId());
            if(client.isEmpty()){
                log.warn("Error ! Client missing in current Vente");
                throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND);
            }
            ClientDto clientDto = currentVente.getClient();


            Optional<Dette> detteEntity = detteRepo.findById(currentVente.getClient().getDetteClient().getId());
            if(detteEntity.isEmpty()){
                log.warn("Error ! Dette missing in updated Vente");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }

            DetteDto detteObject = clientDto.getDetteClient();

            detteObject.setSome(detteObject.getSome() - dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

//New adding

        //verification de la suffisance de stock !

        List<LigneVenteDto> listeDesVente = vente.getLigneVenteList();
        if(listeDesVente.isEmpty()){
            throw new IllegalArgumentException("You can't complete a selling without any product");
        }
        listeDesVente.stream().forEach((LigneVenteDto ligneVenteDto)-> {
            Optional<Produit> produit = produitRepo.findById(ligneVenteDto.getProduit().getId());
            if(produit.isEmpty()){
                log.warn("Product missing !");
                throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+ligneVenteDto.getProduit().getId(),ErrorCodes.PRODUIT_NOT_FOUND);
            }
            ProduitDto produitDto = ligneVenteDto.getProduit();
            Integer produitQtt = produitDto.getQuantiteDisponible();
            if(ligneVenteDto.getQuantite() > produitQtt){
                log.warn("Insuffisant stock");
                throw new OutOfException(ErrorCodes.OUT_OF_STOCK.getDescription()+" you have only "+produitQtt,ErrorCodes.OUT_OF_STOCK,produitQtt);
            }
            produitDto.setQuantiteDisponible(produitQtt - ligneVenteDto.getQuantite());
            produitRepo.save(ProduitDto.toEntity(produitDto));
            // register the line of selling !
            ligneVenteRepo.save(LigneVenteDto.toEntity(ligneVenteDto));
        });

        //traitement de compte
        Optional<Compte> compte = compteRepo.findById(vente.getCompte().getId());
        if(compte.isEmpty()){
            log.warn("Compte missing !");
            throw  new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
        }
        CompteDto currentCompte = vente.getCompte();
        Double balance = currentCompte.getCredit();
        currentCompte.setCredit(balance + vente.getSomePaye()) ;
        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de client et des dettes
        if(vente.getPrixTotaleVente() < vente.getPrixApresRemise()){
            log.warn("prixAchatTotal should be greater than prixApresRemise");
            throw new IllegalArgumentException("prixAchatTotal should be greater than prixApresRemise");
        }
        Double dette = vente.getPrixApresRemise() - vente.getSomePaye();
        if(dette != 0.0){
            Optional<Client> client = clientRepo.findById(vente.getClient().getId());
            if(client.isEmpty()){
                log.warn("Client missing !");
                throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND);
            }
            ClientDto clientDto = vente.getClient();


            Optional<Dette> detteEntity = detteRepo.findById(vente.getClient().getDetteClient().getId());
            if(detteEntity.isEmpty()){
                log.warn("Dette missing !");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }

            DetteDto detteObject = clientDto.getDetteClient();

            detteObject.setSome(detteObject.getSome() + dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

        return venteRepo.save(VenteDto.toEntity(vente)).getId();
    }

    public VenteDto findById(Integer id) {
        if(id == null){
            log.error("Vente id est null");
            return null;
        }

        Vente vente = venteRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.VENTE_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.VENTE_NOT_FOUND));
        return VenteDto.fromEntity(vente);
    }

    public List<VenteDto> findAll(){
        return venteRepo.findAll().stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }




    @Transactional
    public void delete(Integer id) {
        if(id==null){
            log.error("Vente Id is not valid");
        }
        //Implementing update logique (test)
        if(venteRepo.existsById(id)){
            VenteDto currentVente = VenteDto.fromEntity(venteRepo.findById(id).get());

            //verification de la suffisance de stock !

            List<LigneVenteDto> listeDesVentes = currentVente.getLigneVenteList();
            if(listeDesVentes == null || listeDesVentes.isEmpty()){
                throw new IllegalArgumentException("Enable to delete ! existing of a selling without any lines is impossible ");
            }
            listeDesVentes.stream().forEach((LigneVenteDto ligneVenteDto)-> {
                Optional<Produit> produit = produitRepo.findById(ligneVenteDto.getProduit().getId());
                if(produit.isEmpty()){
                    log.warn("Enable to delete ! Product missing in the line");
                    throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+ligneVenteDto.getProduit().getId(),ErrorCodes.PRODUIT_NOT_FOUND);
                }
                ProduitDto produitDto = ligneVenteDto.getProduit();
                Integer produitQtt = produitDto.getQuantiteDisponible();
                produitDto.setQuantiteDisponible(produitQtt + ligneVenteDto.getQuantite());
                produitRepo.save(ProduitDto.toEntity(produitDto));

                ligneVenteRepo.deleteById(ligneVenteDto.getId());
            });

            //traitement de compte
            Optional<Compte> compte = compteRepo.findById(currentVente.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Enable to delete, Compte missing in the current vente !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentVente.getCompte();
            Double balance = currentCompte.getCredit();
            if(currentVente.getSomePaye()>balance){
                log.warn("Enable to delete, Balance insuffisante when restoring vente informations");
                throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
            }
            currentCompte.setCredit(balance - currentVente.getSomePaye()) ;
            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de client et des dettes
            if(currentVente.getPrixTotaleVente() < currentVente.getPrixApresRemise()){
                log.warn("Enable to delete ! prixVenteTotal should be greater than prixApresRemise");
                throw new IllegalArgumentException("prixVenteTotal should be greater than prixApresRemise");
            }
            //typically i should make a verification if prix is less than the prix paye but i think about if he pays more than the buying price and mkach sarf !

            Double dette = currentVente.getPrixApresRemise() - currentVente.getSomePaye();
            Optional<Client> client = clientRepo.findById(currentVente.getClient().getId());
            if(client.isEmpty()){
                log.warn("Enable to delete ! Client missing in updated Vente");
                throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND);
            }
            ClientDto clientDto = currentVente.getClient();


            Optional<Dette> detteEntity = detteRepo.findById(currentVente.getClient().getDetteClient().getId());
            if(detteEntity.isEmpty()){
                log.warn("Enable to delete ! Dette missing in updated Vente");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }

            DetteDto detteObject = clientDto.getDetteClient();

            detteObject.setSome(detteObject.getSome() - dette);
            detteRepo.save(DetteDto.toEntity(detteObject));

            //Setting is Delete to true to do the logical deleting !
            currentVente.setIsDeleted(true);
            venteRepo.save(VenteDto.toEntity(currentVente));
        }
        else {
            throw new EntityNotFoundException(ErrorCodes.TRANSACTION_NOT_FOUND.getDescription(),ErrorCodes.TRANSACTION_NOT_FOUND);
        }

    }
}
