package com.stockManagment.api.produit;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid ProduitDto produit){
        return ResponseEntity.ok(produitService.save(produit));
    }

    @GetMapping
    public ResponseEntity<ProduitDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(produitService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProduitDto>> findAll(){
        return ResponseEntity.ok(produitService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        produitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
