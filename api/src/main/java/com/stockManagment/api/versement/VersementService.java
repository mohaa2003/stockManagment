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
import com.stockManagment.api.exceptions.OutOfException;
import com.stockManagment.api.transaction.TransactionDto;
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
public class VersementService {

    private final CompteRepo compteRepo;
    private final VersementRepo versementRepo;
    private final DetteRepo detteRepo;

    @Transactional
    public Integer save(VersementDto versement) {
    //Updating logic
        if(versementRepo.existsById(versement.getId())){
            VersementDto currentVersement = VersementDto.fromEntity(versementRepo.findById(versement.getId()).get());


            Optional<Compte> compte = compteRepo.findById(currentVersement.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Can not update! Compte missing !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentVersement.getCompte();
            Double some = currentVersement.getSome();
            Double balance = currentCompte.getCredit();
            DetteType type = currentVersement.getTypeVersement();
            if(type == DetteType.POUR){
                if(balance < some){
                    log.warn("Can not update ! Balance insuffisante");
                    throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
                }
                currentCompte.setCredit(balance - some) ;
            }
            else{
                currentCompte.setCredit(balance + some) ;
            }

            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de dette

            Optional<Dette> detteEntity = detteRepo.findById(currentVersement.getDette().getId());
            if(detteEntity.isEmpty()){
                log.warn("Can not update! Dette missing !");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            DetteDto detteObject = currentVersement.getDette();
            if(type == DetteType.POUR){
                detteObject.setSome(detteObject.getSome() + some);
            }
            else{
                detteObject.setSome(detteObject.getSome() - some);
            }

            detteRepo.save(DetteDto.toEntity(detteObject));

        }

//  New adding
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
            if(some>balance){
                log.warn("Balance insuffisante");
                throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
            }
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

    public VersementDto findById(Integer id) {
        if(id == null){
            log.error("versement id est null");
            return null;
        }

        Versement versement = versementRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.VERSEMENT_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.VERSEMENT_NOT_FOUND));
        return VersementDto.fromEntity(versement);
    }

    public List<VersementDto> findAll(){
        return versementRepo.findAll().stream().map(VersementDto::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Integer id) {
        if (id == null){
            log.error("Transaction Id not valid");
        }

        if(versementRepo.existsById(id)){
            VersementDto currentVersement = VersementDto.fromEntity(versementRepo.findById(id).get());


            Optional<Compte> compte = compteRepo.findById(currentVersement.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Error! Compte missing !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentVersement.getCompte();
            Double some = currentVersement.getSome();
            Double balance = currentCompte.getCredit();
            DetteType type = currentVersement.getTypeVersement();
            if(type == DetteType.POUR){
                if(balance < some){
                    log.warn("Error ! Balance insuffisante");
                    throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
                }
                currentCompte.setCredit(balance - some) ;
            }
            else{
                currentCompte.setCredit(balance + some) ;
            }

            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de dette

            Optional<Dette> detteEntity = detteRepo.findById(currentVersement.getDette().getId());
            if(detteEntity.isEmpty()){
                log.warn("Error! Dette missing !");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            DetteDto detteObject = currentVersement.getDette();
            if(type == DetteType.POUR){
                detteObject.setSome(detteObject.getSome() + some);
            }
            else{
                detteObject.setSome(detteObject.getSome() - some);
            }

            detteRepo.save(DetteDto.toEntity(detteObject));
            
        }
        else {
            throw new EntityNotFoundException(ErrorCodes.VERSEMENT_NOT_FOUND.getDescription(),ErrorCodes.VERSEMENT_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if(id == null){
            log.error("Versement id is null");
        }
        else{
            if(versementRepo.existsById(id)){
                VersementDto currentVersement = VersementDto.fromEntity(versementRepo.findById(id).get());
                if(!currentVersement.getIsDeleted()){
                    throw new EntityNotFoundException(ErrorCodes.VERSEMENT_NOT_FOUND.getDescription() + " logically deleted ! can't delete directly active one",ErrorCodes.VERSEMENT_NOT_FOUND);
                }
                else{
                    versementRepo.deleteById(id);
                }
            }
            else {
                throw new EntityNotFoundException(ErrorCodes.VERSEMENT_NOT_FOUND.getDescription(),ErrorCodes.VERSEMENT_NOT_FOUND);
            }
        }
    }
}
