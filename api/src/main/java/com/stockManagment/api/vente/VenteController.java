package com.stockManagment.api.vente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VenteController {
    private final VenteService venteService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid VenteDto vente){
        return ResponseEntity.ok(venteService.save(vente));
    }

    @GetMapping
    public ResponseEntity<VenteDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(venteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VenteDto>> findAll(){
        return ResponseEntity.ok(venteService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        venteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
