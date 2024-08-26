package com.stockManagment.api.agent;
import com.stockManagment.api.dette.*;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
import com.stockManagment.api.fournisseur.Fournisseur;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgentService {
    private final AgentRepo agentRepo;
    private final DetteRepo detteRepo;

    public Integer save(AgentDto agent) {
        if (agent.getDetteAutre() == null){
            DetteAutreDto dette = new DetteAutreDto();
            dette.setEntreprise(agent.getEntreprise());
            dette.setAgent(agent);
            detteRepo.save(DetteAutreDto.toEntity(dette));
            agent.setDetteAutre(DetteAutreDto.fromEntity(detteRepo.save(DetteAutreDto.toEntity(dette))));
        }
        return AgentDto.fromEntity(agentRepo.save(AgentDto.toEntity(agent))).getId();
    }

    public AgentDto findById(Integer id) {
        if(id == null){
            log.error("Agent id est null");
            return null;
        }

        Agent agent = agentRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.AGENT_NOT_FOUND));
        return AgentDto.fromEntity(agent);
    }

    public List<AgentDto> findAll(){
        return agentRepo.findAll().stream().map(AgentDto::fromEntity).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Agent non valid !");
        }
        Optional<Agent> agent = agentRepo.findById(id);
        if(agent.isPresent()){
            Optional<Dette> dette = detteRepo.findById(agent.get().getDette().getId());
            if(dette.isPresent()){
                DetteDto detteDto = DetteAutreDto.fromEntity(dette.get());
                if (detteDto.getSome() != 0){
                    throw new OutOfException(ErrorCodes.HAS_DEBT.getDescription(),ErrorCodes.HAS_DEBT,detteDto.getSome());
                }
            }

        }
        agentRepo.deleteById(id);
    }
}
