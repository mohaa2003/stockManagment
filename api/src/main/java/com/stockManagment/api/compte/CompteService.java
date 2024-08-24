package com.stockManagment.api.compte;

import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
