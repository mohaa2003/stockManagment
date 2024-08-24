package com.stockManagment.api.dette;

import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.versement.VersementDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DetteClientDto extends DetteDto {
    private List<VersementDto> versementList;
    private ClientDto client;

    public DetteClientDto(Integer id, Double some, EntrepriseDto entreprise, List<VersementDto> versementClientList, ClientDto client) {
        super(id, some, entreprise);
        this.versementList = versementClientList;
        this.client = client;
    }

    public static DetteClientDto fromEntity(DetteClient entity) {
        if (entity == null) {
            return null;
        }
        return new DetteClientDto(
                entity.getId(),
                entity.getSome(),
                EntrepriseDto.fromEntity(entity.getEntreprise()),
                entity.getVersementList().stream().map(VersementDto::fromEntity).collect(Collectors.toList()),
                ClientDto.fromEntity(entity.getClient())
        );
    }

    public static DetteClient toEntity(DetteClientDto dto) {
        if (dto == null) {
            return null;
        }
        DetteClient entity = new DetteClient();
        entity.setId(dto.getId());
        entity.setSome(dto.getSome());
        entity.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));
        entity.setVersementList(dto.getVersementList().stream().map(VersementDto::toEntity).collect(Collectors.toList()));
        entity.setClient(ClientDto.toEntity(dto.getClient()));
        return entity;
    }
}

