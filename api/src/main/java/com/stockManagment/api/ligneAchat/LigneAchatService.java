package com.stockManagment.api.ligneAchat;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LigneAchatService {
    private final LigneAchatRepo ligneAchatRepo;

    public LigneAchatDto findById(Integer id) {

        if(id == null){
            log.error("ligne achat id est null");
            return null;
        }
        LigneAchat ligneAchat = ligneAchatRepo.findById(id)

                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.LIGNE_ACHAT_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.LIGNE_ACHAT_NOT_FOUND));

        return LigneAchatDto.fromEntity(ligneAchat);

    }

    public List<LigneAchatDto> findAll(){

        return ligneAchatRepo.findAll().stream().map(LigneAchatDto::fromEntity).collect(Collectors.toList());

    }

}
