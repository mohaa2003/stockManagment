package com.stockManagment.api.achat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class AchatController {

    private final AchatService achatService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid AchatDto achat){
        return ResponseEntity.ok(achatService.save(achat));
    }

    public ResponseEntity<AchatDto> findById(Integer id){
        return ResponseEntity.ok(achatService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AchatDto>> findAll(){
        return ResponseEntity.ok(achatService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        achatService.delete(id);
        return ResponseEntity.ok().build();
    }
}
