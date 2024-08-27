package com.stockManagment.api.compte;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompteController {

    private final CompteService compteService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid CompteDto compte){
        return ResponseEntity.ok(compteService.save(compte));
    }

    @GetMapping
    public ResponseEntity<CompteDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(compteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompteDto>> findAll(){
        return ResponseEntity.ok(compteService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        compteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
