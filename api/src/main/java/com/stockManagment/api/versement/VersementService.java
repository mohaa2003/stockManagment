package com.stockManagment.api.versement;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteRepo;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteDto;
import com.stockManagment.api.dette.DetteRepo;
import com.stockManagment.api.dette.DetteType;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class VersementService {

    private final CompteRepo compteRepo;
    private final VersementRepo versementRepo;
    private final DetteRepo detteRepo;

    @Transactional
    public Integer save(VersementDto versement) {

        //traitement de compte
        Optional<Compte> compte = compteRepo.findById(versement.getCompte().getId());
        if(compte.isEmpty()){
            log.warn("Compte missing !");
            throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
        }
        CompteDto currentCompte = versement.getCompte();
        Double some = versement.getSome();
        Double balance = currentCompte.getCredit();
        DetteType type = versement.getTypeVersement();
        if(type == DetteType.POUR){
            currentCompte.setCredit(balance + some) ;
        }
        else{
            currentCompte.setCredit(balance - some) ;
        }

        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de dette

            Optional<Dette> detteEntity = detteRepo.findById(versement.getDette().getId());
            if(detteEntity.isEmpty()){
                log.warn("Dette missing !");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            DetteDto detteObject = versement.getDette();
            if(type == DetteType.POUR){
                detteObject.setSome(detteObject.getSome() - some);
            }
            else{
                detteObject.setSome(detteObject.getSome() + some);
            }

            detteRepo.save(DetteDto.toEntity(detteObject));

        return versementRepo.save(VersementDto.toEntity(versement)).getId();
    }
}
