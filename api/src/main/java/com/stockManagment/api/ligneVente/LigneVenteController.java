package com.stockManagment.api.ligneVente;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LigneVenteController {
    private final LigneVenteService ligneVenteService;


    @GetMapping
    public ResponseEntity<LigneVenteDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(ligneVenteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LigneVenteDto>> findAll(){
        return ResponseEntity.ok(ligneVenteService.findAll());
    }

}
