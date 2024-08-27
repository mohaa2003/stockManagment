package com.stockManagment.api.client;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid ClientDto client){
        return ResponseEntity.ok(clientService.save(client));
    }

    @GetMapping
    public ResponseEntity<ClientDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll(){
        return ResponseEntity.ok(clientService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
