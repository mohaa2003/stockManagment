package com.stockManagment.api.achat;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
}
