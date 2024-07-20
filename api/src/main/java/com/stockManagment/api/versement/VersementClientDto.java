package com.stockManagment.api.versement;

import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.DetteClientDto;
import com.stockManagment.api.dette.DetteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersementClientDto extends VersementDto {
    private ClientDto client;
    private DetteClientDto detteClient;

    public VersementClientDto(Integer id, DetteType typeDette, Double some, CompteDto compte, ClientDto client, DetteClientDto detteClient) {
        super(id, typeDette, some, compte);
        this.client = client;
        this.detteClient = detteClient;
    }

    public static VersementClientDto fromEntity(VersementClient entity) {
        if (entity == null) {
            return null;
        }
        return new VersementClientDto(
                entity.getId(),
                entity.getTypeDette(),
                entity.getSome(),
                CompteDto.fromEntity(entity.getCompte()),
                ClientDto.fromEntity(entity.getClient()),
                DetteClientDto.fromEntity(entity.getDetteClient())
        );
    }

    public static VersementClient toEntity(VersementClientDto dto) {
        if (dto == null) {
            return null;
        }
        VersementClient entity = new VersementClient();
        entity.setId(dto.getId());
        entity.setTypeDette(dto.getTypeDette());
        entity.setSome(dto.getSome());
        entity.setCompte(CompteDto.toEntity(dto.getCompte()));
        entity.setClient(ClientDto.toEntity(dto.getClient()));
        entity.setDetteClient(DetteClientDto.toEntity(dto.getDetteClient()));
        return entity;
    }
}


