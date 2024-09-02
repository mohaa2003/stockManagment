package com.stockManagment.api.client;
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
            ClientDto clientDto = ClientDto.fromEntity(client.get());
            Optional<Dette> dette = detteRepo.findById(clientDto.getDetteClient().getId());
            if(dette.isPresent()){
                DetteDto detteDto = DetteClientDto.fromEntity(dette.get());
                if (detteDto.getSome() != 0){
                    throw new OutOfException(ErrorCodes.HAS_DEBT.getDescription(),ErrorCodes.HAS_DEBT,detteDto.getSome());
                }
            }
            else{
                log.error("Error ! dette doesn't  exist");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            clientDto.setIsDeleted(true);
            clientRepo.save((ClientDto.toEntity(clientDto)));
        }
        else{
            log.error("Error ! client doesn't  exist");
            throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if(id == null){
            log.error("Client id is null");
        }
        else{
            if(clientRepo.existsById(id)){
                ClientDto currentClient = ClientDto.fromEntity(clientRepo.findById(id).get());
                if(!currentClient.getIsDeleted()){
                    throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription() + " logically deleted ! can't delete directly active one",ErrorCodes.CLIENT_NOT_FOUND);
                }
                else{
                    clientRepo.deleteById(id);
                }
            }
            else {
                throw new EntityNotFoundException(ErrorCodes.CLIENT_NOT_FOUND.getDescription(),ErrorCodes.CLIENT_NOT_FOUND);
            }
        }
    }
}

