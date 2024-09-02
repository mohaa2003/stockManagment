package com.stockManagment.api.produit;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
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
        if(produitRepo.existsById(id)){
            ProduitDto produitDto = ProduitDto.fromEntity(produitRepo.findById(id).get());
            if(produitDto.getQuantiteDisponible() != 0){
                throw new OutOfException(ErrorCodes.PRODUCT_EXISTS.getDescription(),ErrorCodes.PRODUCT_EXISTS,produitDto.getQuantiteDisponible());
            }
            else {
                produitDto.setIsDeleted(true);
                produitRepo.save(ProduitDto.toEntity(produitDto));
            }
        }
        else{
            log.warn("Error ! Product doesn't exist");
            throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription(), ErrorCodes.PRODUIT_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if(id == null){
            log.error("Produit id is null");
        }
        else{
            if(produitRepo.existsById(id)){
                ProduitDto currentProduit = ProduitDto.fromEntity(produitRepo.findById(id).get());
                if(!currentProduit.getIsDeleted()){
                    throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription() + " logically deleted ! can't delete directly active one",ErrorCodes.PRODUIT_NOT_FOUND);
                }
                else{
                    produitRepo.deleteById(id);
                }
            }
            else {
                throw new EntityNotFoundException(ErrorCodes.PRODUIT_NOT_FOUND.getDescription(),ErrorCodes.PRODUIT_NOT_FOUND);
            }
        }
    }
}
