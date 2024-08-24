package com.stockManagment.api.vente;

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

import java.util.List;
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

    public Integer save(VenteDto vente) {

        //verification de la suffisance de stock !

        List<LigneVenteDto> listeDesVente = vente.getLigneVenteList();

        listeDesVente.stream().forEach((LigneVenteDto ligneVenteDto)-> {
            Produit produit = produitRepo.findById(ligneVenteDto.getProduit().getId()).orElseThrow(()->new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+ligneVenteDto.getProduit().getId(),ErrorCodes.PRODUIT_NOT_FOUND));
            ProduitDto produitDto = ligneVenteDto.getProduit();
            Integer produitQtt = produitDto.getQuantiteDisponible();
            if(ligneVenteDto.getQuantite() > produitQtt){
                throw new OutOfException(ErrorCodes.OUT_OF_STOCK.getDescription()+" you have only "+produitQtt,ErrorCodes.OUT_OF_STOCK,produitQtt);
            }
            produitDto.setQuantiteDisponible(produitQtt - ligneVenteDto.getQuantite());
            produitRepo.save(ProduitDto.toEntity(produitDto));
            // register the line of selling !
            ligneVenteRepo.save(LigneVenteDto.toEntity(ligneVenteDto));
        });

        //traitement de compte
        Compte compte = compteRepo.findById(vente.getCompte().getId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND));
        CompteDto currentCompte = vente.getCompte();
        Double balance = currentCompte.getCredit();
        currentCompte.setCredit(balance + vente.getSomePaye()) ;
        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de client et des dettes
        Double dette = vente.getPrixApresRemise() - vente.getSomePaye();
        if(dette != 0.0){
            Client client = clientRepo.findById(vente.getClient().getId())
                    .orElseThrow(()->new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND));
            ClientDto clientDto = vente.getClient();


            Dette detteEntity = detteRepo.findById(vente.getClient().getDetteClient().getId())
                    .orElseThrow(()->new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND));

            DetteDto detteObject = clientDto.getDetteClient();

            detteObject.setSome(detteObject.getSome() + dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

        return venteRepo.save(VenteDto.toEntity(vente)).getId();
    }
}
