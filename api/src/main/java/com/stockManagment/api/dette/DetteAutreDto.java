package com.stockManagment.api.dette;

import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.versement.VersementDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DetteAutreDto extends DetteDto {
    private List<VersementDto> versementList;
    private AgentDto agent;

    public DetteAutreDto(Integer id,  Double some, EntrepriseDto entreprise, List<VersementDto> versementList, AgentDto agent) {
        super(id, some, entreprise);
        this.versementList = versementList;
        this.agent = agent;
    }

    public static DetteAutreDto fromEntity(DetteAutre entity) {
        if (entity == null) {
            return null;
        }
        return new DetteAutreDto(
                entity.getId(),
                entity.getSome(),
                EntrepriseDto.fromEntity(entity.getEntreprise()),
                entity.getVersementList().stream().map(VersementDto::fromEntity).collect(Collectors.toList()),
                AgentDto.fromEntity(entity.getAgent())
        );
    }

    public static DetteAutre toEntity(DetteAutreDto dto) {
        if (dto == null) {
            return null;
        }
        DetteAutre entity = new DetteAutre();
        entity.setId(dto.getId());
        entity.setSome(dto.getSome());
        entity.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));
        entity.setVersementList(dto.getVersementList().stream().map(VersementDto::toEntity).collect(Collectors.toList()));
        entity.setAgent(AgentDto.toEntity(dto.getAgent()));
        return entity;
    }
}

