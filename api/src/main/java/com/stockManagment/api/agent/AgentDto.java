package com.stockManagment.api.agent;

import com.stockManagment.api.dette.DetteAutreDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {
    private Integer id;
    private Boolean isDeleted;
    private String nomAgent;
    private String surnomAgent;
    private String numTlph;
    private String fonction;
    private List<TransactionDto> transactionList;
    private DetteAutreDto detteAutre;
    private EntrepriseDto entreprise;

    public static AgentDto fromEntity(Agent agent) {
        if (agent == null) {
            return null;
        }

        return new AgentDto(
                agent.getId(),
                agent.getIsDeleted(),
                agent.getNomAgent(),
                agent.getSurnomAgent(),
                agent.getNumTlph(),
                agent.getFonction(),
                agent.getTransactionList().stream().map(TransactionDto::fromEntity).collect(Collectors.toList()),
                (DetteAutreDto) DetteAutreDto.fromEntity(agent.getDette()),
                EntrepriseDto.fromEntity(agent.getEntreprise())
        );
    }

    public static Agent toEntity(AgentDto agentDto) {
        if (agentDto == null) {
            return null;
        }

        Agent agent = new Agent();
        agent.setId(agentDto.getId());
        agent.setIsDeleted(agentDto.isDeleted);
        agent.setNomAgent(agentDto.getNomAgent());
        agent.setSurnomAgent(agentDto.getSurnomAgent());
        agent.setNumTlph(agentDto.getNumTlph());
        agent.setFonction(agentDto.getFonction());
        agent.setTransactionList(agentDto.getTransactionList().stream().map(TransactionDto::toEntity).collect(Collectors.toList()));
        agent.setDette(DetteAutreDto.toEntity(agentDto.getDetteAutre()));
        agent.setEntreprise(EntrepriseDto.toEntity(agentDto.getEntreprise()));

        return agent;
    }
}
