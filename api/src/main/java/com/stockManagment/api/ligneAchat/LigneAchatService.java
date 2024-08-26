package com.stockManagment.api.ligneAchat;

import com.stockManagment.api.client.Client;
import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.client.ClientRepo;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LigneAchatService {
    private final ClientRepo clientRepo;

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
        clientRepo.deleteById(id);
    }
}
}
