package com.stockManagment.api.compte;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompteService {

    private final CompteRepo compteRepo;

    public Integer save(CompteDto compte) {
        return CompteDto.fromEntity(compteRepo.save(CompteDto.toEntity(compte))).getId();
    }

    public CompteDto findById(Integer id) {
        if(id == null){
            log.error("compte id est null");
            return null;
        }

        Compte compte = compteRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.COMPTE_NOT_FOUND));
        return CompteDto.fromEntity(compte);
    }

    public List<CompteDto> findAll(){
        return compteRepo.findAll().stream().map(CompteDto::fromEntity).collect(Collectors.toList());
    }


//    ---------------------NOT COMPLETED-------------------------

    public void delete(Integer id) {
        if (id == null){
            log.error("Compte non valid !");
        }
        if(compteRepo.existsById(id)){
            CompteDto compteDto = CompteDto.fromEntity(compteRepo.findById(id).get());
            if(compteDto.getCredit() != 0){
                throw new OutOfException(ErrorCodes.COMPTE_HAS_MONEY.getDescription(),ErrorCodes.COMPTE_HAS_MONEY,compteDto.getCredit());
            }
            else {
                compteDto.setIsDeleted(true);
                compteRepo.save(CompteDto.toEntity(compteDto));
            }
        }
        else{
            log.warn("Error ! Compte doesn't exist");
            throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(), ErrorCodes.COMPTE_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if(id == null){
            log.error("Compte id is null");
        }
        else{
            if(compteRepo.existsById(id)){
                CompteDto currentCompte = CompteDto.fromEntity(compteRepo.findById(id).get());
                if(!currentCompte.getIsDeleted()){
                    throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription() + " logically deleted ! can't delete directly active one",ErrorCodes.COMPTE_NOT_FOUND);
                }
                else{
                    compteRepo.deleteById(id);
                }
            }
            else {
                log.error("Error! Copte doesn't exist");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
        }
    }
}

