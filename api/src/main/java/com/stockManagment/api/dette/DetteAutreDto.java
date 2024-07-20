package com.stockManagment.api.dette;

import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.versement.VersementAutreDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DetteAutreDto extends DetteDto {
    private List<VersementAutreDto> versementAutreListList;
    private AgentDto agent;

    public DetteAutreDto(Integer id, DetteType typeDette, Double some, EntrepriseDto entreprise, List<VersementAutreDto> versementAutreListList, AgentDto agent) {
        super(id, typeDette, some, entreprise);
        this.versementAutreListList = versementAutreListList;
        this.agent = agent;
    }

    public static DetteAutreDto fromEntity(DetteAutre entity) {
        if (entity == null) {
            return null;
        }
        return new DetteAutreDto(
                entity.getId(),
                entity.getTypeDette(),
                entity.getSome(),
                EntrepriseDto.fromEntity(entity.getEntreprise()),
                entity.getVersementAutreListList().stream().map(VersementAutreDto::fromEntity).collect(Collectors.toList()),
                AgentDto.fromEntity(entity.getAgent())
        );
    }

    public static DetteAutre toEntity(DetteAutreDto dto) {
        if (dto == null) {
            return null;
        }
        DetteAutre entity = new DetteAutre();
        entity.setId(dto.getId());
        entity.setTypeDette(dto.getTypeDette());
        entity.setSome(dto.getSome());
        entity.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));
        entity.setVersementAutreListList(dto.getVersementAutreListList().stream().map(VersementAutreDto::toEntity).collect(Collectors.toList()));
        entity.setAgent(AgentDto.toEntity(dto.getAgent()));
        return entity;
    }
}

