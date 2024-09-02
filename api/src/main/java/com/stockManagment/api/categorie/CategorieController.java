package com.stockManagment.api.categorie;

import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.agent.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid CategorieDto categorie){
        return ResponseEntity.ok(categorieService.save(categorie));
    }

    @GetMapping
    public ResponseEntity<CategorieDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(categorieService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategorieDto>> findAll(){
        return ResponseEntity.ok(categorieService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        categorieService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping
//    public ResponseEntity deleteById(@PathVariable Integer id){
//        categorieService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }

}
