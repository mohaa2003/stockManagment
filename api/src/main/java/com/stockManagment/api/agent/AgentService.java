package com.stockManagment.api.agent;
import com.stockManagment.api.achat.AchatDto;
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
            AgentDto agentDto = AgentDto.fromEntity(agent.get());
            Optional<Dette> dette = detteRepo.findById(agentDto.getDetteAutre().getId());
            if(dette.isPresent()){
                DetteDto detteDto = DetteAutreDto.fromEntity(dette.get());
                if (detteDto.getSome() != 0){
                    throw new OutOfException(ErrorCodes.HAS_DEBT.getDescription(),ErrorCodes.HAS_DEBT,detteDto.getSome());
                }
            }
            else{
                log.error("Error ! dette doesn't  exist");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            agentDto.setIsDeleted(true);
            agentRepo.save(AgentDto.toEntity(agentDto));
        }
        else{
            log.error("Error ! agent doesn't  exist");
            throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription(),ErrorCodes.AGENT_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if(id == null){
            log.error("Agent id is null");
        }
        else{
            if(agentRepo.existsById(id)){
                AgentDto currentAgent = AgentDto.fromEntity(agentRepo.findById(id).get());
                if(!currentAgent.getIsDeleted()){
                    throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription() + " logically deleted ! can't delete directly active one",ErrorCodes.AGENT_NOT_FOUND);
                }
                else{
                    agentRepo.deleteById(id);
                }
            }
            else {
                throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription(),ErrorCodes.AGENT_NOT_FOUND);
            }
        }
    }
}

