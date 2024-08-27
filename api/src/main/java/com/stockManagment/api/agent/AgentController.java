package com.stockManagment.api.agent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
    @RestController
    public class AgentController {

    private final AgentService agentService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid AgentDto agent){
        return ResponseEntity.ok(agentService.save(agent));
    }

    @GetMapping
    public ResponseEntity<AgentDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(agentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AgentDto>> findAll(){
        return ResponseEntity.ok(agentService.findAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Integer id){
        agentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
