package com.stockManagment.api.entreprise;
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
public class EntrepriseService {
    private final EntrepriseRepo entrepriseRepo;

    public Integer save(EntrepriseDto entreprise) {

        return EntrepriseDto.fromEntity(entrepriseRepo.save(EntrepriseDto.toEntity(entreprise))).getId();

    }

    public EntrepriseDto findById(Integer id) {

        if(id == null){
            log.error("Entreprise id est null");
            return null;
        }
        Entreprise entreprise = entrepriseRepo.findById(id)

                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.ENTREPRISE_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.ENTREPRISE_NOT_FOUND));

        return EntrepriseDto.fromEntity(entreprise);

    }

    public List<EntrepriseDto> findAll(){

        return entrepriseRepo.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());

    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Entreprise non valid !");
        }
        entrepriseRepo.deleteById(id);
    }
}

