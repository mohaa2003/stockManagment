package com.stockManagment.api.vente;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.achat.AchatRepo;
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
import com.stockManagment.api.ligneVente.LigneVenteDto;
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

    private final AchatRepo achatRepo;
    private final CompteRepo compteRepo;
    private final FournisseurRepo fournisseurRepo;
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
        });

        //I stopped there !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //traitement de compte
        Compte compte = compteRepo.findById(vente.getCompte().getId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND));
        CompteDto currentCompte = vente.getCompte();
        Double balance = currentCompte.getCredit();
        if(vente.getSomePaye()>balance){
            throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
        }
        currentCompte.setCredit(currentCompte.getCredit() - achat.getSomePaye()) ;
        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de fournisseur et des dettes
        Double dette = achat.getPrixApresRemise() - achat.getSomePaye();
        if(dette != 0.0){
            Fournisseur fournisseur = fournisseurRepo.findById(achat.getFournisseur().getId())
                    .orElseThrow(()->new EntityNotFoundException(ErrorCodes.FOURNISSEUR_NOT_FOUND.getDescription(),ErrorCodes.FOURNISSEUR_NOT_FOUND));
            FournisseurDto fournisseurDto = achat.getFournisseur();


            Dette detteEntity = detteRepo.findById(achat.getFournisseur().getDetteFournisseur().getId())
                    .orElseThrow(()->new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND));

            DetteDto detteObject = fournisseurDto.getDetteFournisseur();

            detteObject.setSome(detteObject.getSome() + dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

        return achatRepo.save(AchatDto.toEntity(achat)).getId();
    }
}
