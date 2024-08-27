package com.stockManagment.api.fournisseur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FournisseurController {
    private final FournisseurService fournisseurService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid FournisseurDto fournisseur){
        return ResponseEntity.ok(fournisseurService.save(fournisseur));
    }

    @GetMapping
    public ResponseEntity<FournisseurDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FournisseurDto>> findAll(){
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        fournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
