package com.stockManagment.api.categorie;
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
public class CategorieService {
    private final CategorieRepo categorieRepo;

    public Integer save(CategorieDto categorie) {
        return CategorieDto.fromEntity(categorieRepo.save(CategorieDto.toEntity(categorie))).getId();
    }

    public CategorieDto findById(Integer id) {
        if(id == null){
            log.error("Categorie id est null");
            return null;
        }

        Categorie categorie = categorieRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.CATEGORY_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.CATEGORY_NOT_FOUND));
        return CategorieDto.fromEntity(categorie);
    }

    public List<CategorieDto> findAll(){
        return categorieRepo.findAll().stream().map(CategorieDto::fromEntity).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Categorie non valid !");
        }
        categorieRepo.deleteById(id);
    }
}
