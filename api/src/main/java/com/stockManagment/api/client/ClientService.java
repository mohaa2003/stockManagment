package com.stockManagment.api.client;
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
public class ClientService {
    private final ClientRepo clientRepo;
    private final DetteRepo detteRepo;

    public Integer save(ClientDto client) {
        if (client.getDetteClient() == null){
            DetteClientDto dette = new DetteClientDto();
            dette.setEntreprise(client.getEntreprise());
            dette.setClient(client);
            detteRepo.save(DetteClientDto.toEntity(dette));
            client.setDetteClient(DetteClientDto.fromEntity(detteRepo.save(DetteClientDto.toEntity(dette))));
        }
        return ClientDto.fromEntity(clientRepo.save(ClientDto.toEntity(client))).getId();
    }

    public ClientDto findById(Integer id) {
        if(id == null){
            log.error("client id est null");
            return null;
        }

        Client client = clientRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.CLIENT_NOT_FOUND));
        return ClientDto.fromEntity(client);
    }

    public List<ClientDto> findAll(){
        return clientRepo.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Client non valid !");
        }
        Optional<Client> client = clientRepo.findById(id);
        if(client.isPresent()){
            Optional<Dette> dette = detteRepo.findById(client.get().getDette().getId());
            if(dette.isPresent()){
                DetteDto detteDto = DetteClientDto.fromEntity(dette.get());
                if (detteDto.getSome() != 0){
                    throw new OutOfException(ErrorCodes.HAS_DEBT.getDescription(),ErrorCodes.HAS_DEBT);
                }
            }

        }
        clientRepo.deleteById(id);
    }
}
