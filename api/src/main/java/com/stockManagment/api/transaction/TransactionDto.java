package com.stockManagment.api.transaction;

import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Integer id;
    private Boolean isDeleted;
    private TypeTransaction typeTransaction;
    private Double intitule;
    private Double prix;
    private Double prixPaye;
    private AgentDto agent;
    private CompteDto compte;
    private EntrepriseDto entreprise;
    private String comment;

    public static TransactionDto fromEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return new TransactionDto(
                transaction.getId(),
                transaction.getIsDeleted(),
                transaction.getTypeTransaction(),
                transaction.getIntitule(),
                transaction.getPrix(),
                transaction.getPrixPaye(),
                AgentDto.fromEntity(transaction.getAgent()),
                CompteDto.fromEntity(transaction.getCompte()),
                EntrepriseDto.fromEntity(transaction.getEntreprise()),
                transaction.getComment()
        );
    }

    public static Transaction toEntity(TransactionDto transactionDto) {
        if (transactionDto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setIsDeleted(transactionDto.getIsDeleted());
        transaction.setTypeTransaction(transactionDto.getTypeTransaction());
        transaction.setIntitule(transactionDto.getIntitule());
        transaction.setPrix(transactionDto.getPrix());
        transaction.setPrixPaye(transactionDto.getPrixPaye());
        transaction.setAgent(AgentDto.toEntity(transactionDto.getAgent()));
        transaction.setCompte(CompteDto.toEntity(transactionDto.getCompte()));
        transaction.setEntreprise(EntrepriseDto.toEntity(transactionDto.getEntreprise()));
        transaction.setComment(transactionDto.getComment());

        return transaction;
    }
}
