package com.stockManagment.api.compte;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CompteController {

    private final CompteService compteService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid CompteDto compte){
        return ResponseEntity.ok(compteService.save(compte));
    }

    @GetMapping
    public ResponseEntity<CompteDto> findById(Integer id){
        return ResponseEntity.ok(compteService.findById(id));
    }
}
