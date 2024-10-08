package com.stockManagment.api.client;

import com.stockManagment.api.dette.DetteClientDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.vente.VenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Integer id;
    private Boolean isDeleted;
    private String nomClient;
    private String surnomClient;
    private String numTlph;
    private String fonction;
    private List<VenteDto> venteList;
    private DetteClientDto detteClient;
    private EntrepriseDto entreprise;

    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            return null;
        }

        return new ClientDto(
                client.getId(),
                client.getIsDeleted(),
                client.getNomClient(),
                client.getSurnomClient(),
                client.getNumTlph(),
                client.getFonction(),
                client.getVenteList().stream().map(VenteDto::fromEntity).collect(Collectors.toList()),
                (DetteClientDto) DetteClientDto.fromEntity(client.getDette()),
                EntrepriseDto.fromEntity(client.getEntreprise())
        );
    }

    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setIsDeleted(clientDto.isDeleted);
        client.setNomClient(clientDto.getNomClient());
        client.setSurnomClient(clientDto.getSurnomClient());
        client.setNumTlph(clientDto.getNumTlph());
        client.setFonction(clientDto.getFonction());
        client.setVenteList(clientDto.getVenteList().stream().map(VenteDto::toEntity).collect(Collectors.toList()));
        client.setDette(DetteClientDto.toEntity(clientDto.getDetteClient()));
        client.setEntreprise(EntrepriseDto.toEntity(clientDto.getEntreprise()));

        return client;
    }
}
