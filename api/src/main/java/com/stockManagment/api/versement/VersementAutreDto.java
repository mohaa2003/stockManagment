package com.stockManagment.api.versement;

import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.DetteAutreDto;
import com.stockManagment.api.dette.DetteType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersementAutreDto extends VersementDto {
    private DetteAutreDto detteAutre;
    private AgentDto agent;

    public VersementAutreDto(Integer id, DetteType typeDette, Double some, CompteDto compte, DetteAutreDto detteAutre, AgentDto agent) {
        super(id, typeDette, some, compte);
        this.detteAutre = detteAutre;
        this.agent = agent;
    }

    public static VersementAutreDto fromEntity(VersementAutre entity) {
        if (entity == null) {
            return null;
        }
        return new VersementAutreDto(
                entity.getId(),
                entity.getTypeDette(),
                entity.getSome(),
                CompteDto.fromEntity(entity.getCompte()),
                (DetteAutreDto) DetteAutreDto.fromEntity(entity.getDette()),
                AgentDto.fromEntity(entity.getAgent())
        );
    }

    public static VersementAutre toEntity(VersementAutreDto dto) {
        if (dto == null) {
            return null;
        }
        VersementAutre entity = new VersementAutre();
        entity.setId(dto.getId());
        entity.setTypeDette(dto.getTypeDette());
        entity.setSome(dto.getSome());
        entity.setCompte(CompteDto.toEntity(dto.getCompte()));
        entity.setDette(DetteAutreDto.toEntity(dto.getDetteAutre()));
        entity.setAgent(AgentDto.toEntity(dto.getAgent()));
        return entity;
    }
}

