package com.stockManagment.api.ligneVente;

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
public class LigneVenteService {
    private final LigneVenteRepo ligneVenteRepo;

    public LigneVenteDto findById(Integer id) {

        if(id == null){
            log.error("ligne vente id est null");
            return null;
        }
        LigneVente ligneVente = ligneVenteRepo.findById(id)

                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.LIGNE_VENTE_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.LIGNE_VENTE_NOT_FOUND));

        return LigneVenteDto.fromEntity(ligneVente);

    }

    public List<LigneVenteDto> findAll(){

        return ligneVenteRepo.findAll().stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList());

    }
}
