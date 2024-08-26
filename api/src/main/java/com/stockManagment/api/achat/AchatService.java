package com.stockManagment.api.achat;

import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteRepo;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteDto;
import com.stockManagment.api.dette.DetteRepo;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.fournisseur.FournisseurDto;
import com.stockManagment.api.fournisseur.FournisseurRepo;
import com.stockManagment.api.ligneAchat.LigneAchatDto;
import com.stockManagment.api.ligneAchat.LigneAchatRepo;
import com.stockManagment.api.produit.Produit;
import com.stockManagment.api.produit.ProduitDto;
import com.stockManagment.api.produit.ProduitRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class AchatService {
    private final AchatRepo achatRepo;
    private final CompteRepo compteRepo;
    private final FournisseurRepo fournisseurRepo;
    private final DetteRepo detteRepo;
    private final ProduitRepo produitRepo;
    private final LigneAchatRepo ligneAchatRepo;

    @Transactional
    public Integer save(AchatDto achat) {

        //verification de la suffisance de stock !

        List<LigneAchatDto> listeDesAchat = achat.getLigneAchatList();

        listeDesAchat.stream().forEach((LigneAchatDto ligneAchatDto)-> {
            Optional<Produit> produit = produitRepo.findById(ligneAchatDto.getProduit().getId());
            if(produit.isEmpty()){
                log.warn("Produit missing !");
                throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+ligneAchatDto.getProduit().getId(),ErrorCodes.PRODUIT_NOT_FOUND);
            }
            ProduitDto produitDto = ligneAchatDto.getProduit();
            Integer produitQtt = produitDto.getQuantiteDisponible();
            produitDto.setQuantiteDisponible(produitQtt + ligneAchatDto.getQuantite());
            produitRepo.save(ProduitDto.toEntity(produitDto));

            ligneAchatRepo.save(LigneAchatDto.toEntity(ligneAchatDto));
        });

        //traitement de compte
        Optional<Compte> compte = compteRepo.findById(achat.getCompte().getId());
        if(compte.isEmpty()){
            log.warn("Compte missing !");
            throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
        }
        CompteDto currentCompte = achat.getCompte();
        Double balance = currentCompte.getCredit();
        if(achat.getSomePaye()>balance){
            log.warn("Balance insuffisante");
            throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
        }
        currentCompte.setCredit(currentCompte.getCredit() - achat.getSomePaye()) ;
        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de fournisseur et des dettes
        if(achat.getPrixAchatTotal() < achat.getPrixApresRemise()){
            log.warn("prixAchatTotal should be greater than prixApresRemise");
            throw new IllegalArgumentException("prixAchatTotal should be greater than prixApresRemise");
        }
        //typically i should make a verification if prix is less than the prix paye but i think about if he pays more than the buying price and mkach sarf !

        Double dette = achat.getPrixApresRemise() - achat.getSomePaye();
        if(dette != 0.0){
            Optional<Fournisseur> fournisseur = fournisseurRepo.findById(achat.getFournisseur().getId());
            if(fournisseur.isEmpty()){
                log.warn("Fournisseur missing");
                throw new EntityNotFoundException(ErrorCodes.FOURNISSEUR_NOT_FOUND.getDescription(),ErrorCodes.FOURNISSEUR_NOT_FOUND);
            }
            FournisseurDto fournisseurDto = achat.getFournisseur();


            Optional<Dette> detteEntity = detteRepo.findById(achat.getFournisseur().getDetteFournisseur().getId());
            if(detteEntity.isEmpty()){
                log.warn("Dette missing");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }

            DetteDto detteObject = fournisseurDto.getDetteFournisseur();

            detteObject.setSome(detteObject.getSome() - dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

        return AchatDto.fromEntity(achatRepo.save(AchatDto.toEntity(achat))).getId();
    }

    public AchatDto findById(Integer id) {
        return AchatDto.fromEntity(achatRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.ACHAT_NOT_FOUND.getDescription(),ErrorCodes.ACHAT_NOT_FOUND)));
    }
}
