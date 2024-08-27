package com.stockManagment.api.transaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid TransactionDto transaction){
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping
    public ResponseEntity<TransactionDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        transactionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
