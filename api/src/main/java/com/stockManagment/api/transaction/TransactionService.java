package com.stockManagment.api.transaction;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.agent.AgentRepo;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.CompteRepo;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteDto;
import com.stockManagment.api.dette.DetteRepo;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import com.stockManagment.api.exceptions.OutOfException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final CompteRepo compteRepo;
    private final AgentRepo agentRepo;
    private final DetteRepo detteRepo;

    @Transactional
    public Integer save(TransactionDto transaction) {
    //Implementing updating logic
        if(transactionRepo.existsById(transaction.getId())){
            TransactionDto currentTransaction = TransactionDto.fromEntity(transactionRepo.findById(transaction.getId()).get());
            //traitement de compte
            Optional<Compte> compte = compteRepo.findById(currentTransaction.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Error! Compte missing !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentTransaction.getCompte();
            Double balance = currentCompte.getCredit();
            if(currentTransaction.getTypeTransaction() == TypeTransaction.GAIN){
                if(currentTransaction.getPrixPaye()>balance){
                    log.warn("Error! Balance insuffisante");
                    throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
                }
                currentCompte.setCredit(balance - currentTransaction.getPrixPaye()) ;
            }
            else{
                currentCompte.setCredit(balance + currentTransaction.getPrixPaye()) ;
            }

            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de client et des dettes

            Double dette = currentTransaction.getPrix() + currentTransaction.getPrixPaye();
            if(currentTransaction.getAgent() != null){
                Optional<Agent> agent = agentRepo.findById(currentTransaction.getAgent().getId());
                if(agent.isEmpty()){
                    log.warn("Error! Agent missing !");
                    throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription(),ErrorCodes.AGENT_NOT_FOUND);
                }
                AgentDto agentDto = currentTransaction.getAgent();


                Optional<Dette> detteEntity = detteRepo.findById(currentTransaction.getAgent().getDetteAutre().getId());
                if(detteEntity.isEmpty()){
                    log.warn("Error! Dette missing !");
                    throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
                }
                DetteDto detteObject = agentDto.getDetteAutre();

                detteObject.setSome(detteObject.getSome() - dette);
                detteRepo.save(DetteDto.toEntity(detteObject));
            }

        }






//New adding
        //traitement de compte
        Optional<Compte> compte = compteRepo.findById(transaction.getCompte().getId());
        if(compte.isEmpty()){
            log.warn("Compte missing !");
            throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
        }
        CompteDto currentCompte = transaction.getCompte();
        Double balance = currentCompte.getCredit();
        if(transaction.getTypeTransaction() == TypeTransaction.GAIN){
            currentCompte.setCredit(balance + transaction.getPrixPaye()) ;
        }
        else{
            if(transaction.getPrixPaye()>balance){
                log.warn("Balance insuffisante");
                throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
            }
            currentCompte.setCredit(balance - transaction.getPrixPaye()) ;
        }

        compteRepo.save(CompteDto.toEntity(currentCompte));

        //traitement de client et des dettes

        Double dette = transaction.getPrix() - transaction.getPrixPaye();
        if(dette != 0.0  && transaction.getAgent() != null){
            Optional<Agent> agent = agentRepo.findById(transaction.getAgent().getId());
            if(agent.isEmpty()){
                log.warn("Agent missing !");
                throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription(),ErrorCodes.AGENT_NOT_FOUND);
            }
            AgentDto agentDto = transaction.getAgent();


            Optional<Dette> detteEntity = detteRepo.findById(transaction.getAgent().getDetteAutre().getId());
            if(detteEntity.isEmpty()){
                log.warn("Dette missing !");
                throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
            }
            DetteDto detteObject = agentDto.getDetteAutre();

            detteObject.setSome(detteObject.getSome() + dette);
            detteRepo.save(DetteDto.toEntity(detteObject));
        }

        return transactionRepo.save(TransactionDto.toEntity(transaction)).getId();
    }

    public TransactionDto findById(Integer id) {
        if(id == null){
            log.error("Transaction id est null");
            return null;
        }

        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.TRANSACTION_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.TRANSACTION_NOT_FOUND));
        return TransactionDto.fromEntity(transaction);
    }


    public List<TransactionDto> findAll(){
        return transactionRepo.findAll().stream().map(TransactionDto::fromEntity).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        if(transactionRepo.existsById(id)){
            TransactionDto currentTransaction = TransactionDto.fromEntity(transactionRepo.findById(id).get());
            //traitement de compte
            Optional<Compte> compte = compteRepo.findById(currentTransaction.getCompte().getId());
            if(compte.isEmpty()){
                log.warn("Enable to delete! Compte missing !");
                throw new EntityNotFoundException(ErrorCodes.COMPTE_NOT_FOUND.getDescription(),ErrorCodes.COMPTE_NOT_FOUND);
            }
            CompteDto currentCompte = currentTransaction.getCompte();
            Double balance = currentCompte.getCredit();
            if(currentTransaction.getTypeTransaction() == TypeTransaction.GAIN){
                if(currentTransaction.getPrixPaye()>balance){
                    log.warn("Enable to delete! Balance insuffisante");
                    throw new OutOfException("You have only "+balance+" in your balance",ErrorCodes.OUT_OF_MONEY,balance);
                }
                currentCompte.setCredit(balance - currentTransaction.getPrixPaye()) ;
            }
            else{
                currentCompte.setCredit(balance + currentTransaction.getPrixPaye()) ;
            }

            compteRepo.save(CompteDto.toEntity(currentCompte));

            //traitement de client et des dettes

            Double dette = currentTransaction.getPrix() + currentTransaction.getPrixPaye();
            if(currentTransaction.getAgent() != null){
                Optional<Agent> agent = agentRepo.findById(currentTransaction.getAgent().getId());
                if(agent.isEmpty()){
                    log.warn("Enable to delete! Agent missing !");
                    throw new EntityNotFoundException(ErrorCodes.AGENT_NOT_FOUND.getDescription(),ErrorCodes.AGENT_NOT_FOUND);
                }
                AgentDto agentDto = currentTransaction.getAgent();


                Optional<Dette> detteEntity = detteRepo.findById(currentTransaction.getAgent().getDetteAutre().getId());
                if(detteEntity.isEmpty()){
                    log.warn("Enable to delete! Dette missing !");
                    throw new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription(),ErrorCodes.DETTE_NOT_FOUND);
                }
                DetteDto detteObject = agentDto.getDetteAutre();

                detteObject.setSome(detteObject.getSome() - dette);
                detteRepo.save(DetteDto.toEntity(detteObject));
            }
            currentTransaction.setIsDeleted(true);
            transactionRepo.save(TransactionDto.toEntity(currentTransaction));
        }
        else {
            throw new EntityNotFoundException(ErrorCodes.TRANSACTION_NOT_FOUND.getDescription(),ErrorCodes.TRANSACTION_NOT_FOUND);
        }
    }
}
