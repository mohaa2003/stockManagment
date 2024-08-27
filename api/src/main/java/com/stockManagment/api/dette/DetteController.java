package com.stockManagment.api.dette;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DetteController {
    private final DetteService detteService;

    @GetMapping
    public ResponseEntity<DetteDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(detteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DetteDto>> findAll(){
        return ResponseEntity.ok(detteService.findAll());
    }


}
