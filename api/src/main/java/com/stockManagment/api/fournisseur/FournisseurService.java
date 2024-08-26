package com.stockManagment.api.fournisseur;
import com.stockManagment.api.dette.*;
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
public class FournisseurService {
    private final FournisseurRepo fournisseurRepo;
    private final DetteRepo detteRepo;

    public Integer save(FournisseurDto fournisseur) {
        if (fournisseur.getDetteFournisseur() == null){
            DetteFournisseurDto dette = new DetteFournisseurDto();
            dette.setEntreprise(fournisseur.getEntreprise());
            dette.setFournisseur(fournisseur);
            detteRepo.save(DetteFournisseurDto.toEntity(dette));
            fournisseur.setDetteFournisseur(DetteFournisseurDto.fromEntity(detteRepo.save(DetteFournisseurDto.toEntity(dette))));
        }
        return FournisseurDto.fromEntity(fournisseurRepo.save(FournisseurDto.toEntity(fournisseur))).getId();

    }

    public FournisseurDto findById(Integer id) {

        if(id == null){
            log.error("Fournisseur id est null");
            return null;
        }
        Fournisseur fournisseur = fournisseurRepo.findById(id)

                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.FOURNISSEUR_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.FOURNISSEUR_NOT_FOUND));

        return FournisseurDto.fromEntity(fournisseur);

    }

    public List<FournisseurDto> findAll(){

        return fournisseurRepo.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());

    }

    public void delete(Integer id) {
        if (id == null){
            log.error("fournisseur non valid !");
        }
        Optional<Fournisseur> fournisseur = fournisseurRepo.findById(id);
        if(fournisseur.isPresent()){
            Optional<Dette> dette = detteRepo.findById(fournisseur.get().getDette().getId());
            if(dette.isPresent()){
                DetteDto detteDto = DetteFournisseurDto.fromEntity(dette.get());
                if (detteDto.getSome() != 0){
                    throw new OutOfException(ErrorCodes.HAS_DEBT.getDescription(),ErrorCodes.HAS_DEBT,detteDto.getSome());
                }
            }

        }

        fournisseurRepo.deleteById(id);
    }
}

