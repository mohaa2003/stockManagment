package com.stockManagment.api.agent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

    @RequiredArgsConstructor
    @RestController
    public class AgentController {

        final AgentService agentService;

//        @PostMapping
//        public Integer save(AgentDto agent){
//            return ResponseEntity.ok(agentService.save(agent));
//        }
}
