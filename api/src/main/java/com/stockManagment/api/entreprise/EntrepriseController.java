package com.stockManagment.api.entreprise;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EntrepriseController {
    private final EntrepriseService entrepriseService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid EntrepriseDto entreprise){
        return ResponseEntity.ok(entrepriseService.save(entreprise));
    }

    @GetMapping
    public ResponseEntity<EntrepriseDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EntrepriseDto>> findAll(){
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        entrepriseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
