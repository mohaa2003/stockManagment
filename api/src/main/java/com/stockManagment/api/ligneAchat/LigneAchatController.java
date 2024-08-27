package com.stockManagment.api.ligneAchat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LigneAchatController {
    private final LigneAchatService ligneAchatService;

    @GetMapping
    public ResponseEntity<LigneAchatDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(ligneAchatService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LigneAchatDto>> findAll(){
        return ResponseEntity.ok(ligneAchatService.findAll());
    }

}
