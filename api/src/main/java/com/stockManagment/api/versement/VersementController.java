package com.stockManagment.api.versement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class VersementController{
    private final VersementService versementService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid VersementDto versement){
        return ResponseEntity.ok(versementService.save(versement));
    }

    @GetMapping
    public ResponseEntity<VersementDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(versementService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VersementDto>> findAll(){
        return ResponseEntity.ok(versementService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        versementService.delete(id);
        return ResponseEntity.ok().build();
    }

}
