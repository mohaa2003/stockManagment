package com.stockManagment.api.produit;
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
public class ProduitService {
    private final ProduitRepo produitRepo;

    public Integer save(ProduitDto produit) {
        return ProduitDto.fromEntity(produitRepo.save(ProduitDto.toEntity(produit))).getId();
    }

    public ProduitDto findById(Integer id) {
        if(id == null){
            log.error("Produit id est null");
            return null;
        }

        Produit produit = produitRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.PRODUIT_NOT_FOUND));
        return ProduitDto.fromEntity(produit);
    }

    public List<ProduitDto> findAll(){
        return produitRepo.findAll().stream().map(ProduitDto::fromEntity).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Produit non valid !");
        }
        produitRepo.deleteById(id);
    }
}
