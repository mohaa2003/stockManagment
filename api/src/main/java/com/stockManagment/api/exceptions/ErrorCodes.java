package com.stockManagment.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum ErrorCodes {
    //codes here
    ACHAT_NOT_FOUND(110,"No Achat Found"),
    AGENT_NOT_FOUND(120,"No Agent Found"),
    CATEGORY_NOT_FOUND(130,"No Category Found"),
    CLIENT_NOT_FOUND(140,"No Client Found"),
    COMPTE_NOT_FOUND(150,"No Compte Found"),
    DETTE_NOT_FOUND(160,"No Dette Found"),
    ENTREPRISE_NOT_FOUND(170,"No Entreprise Found"),
    FOURNISSEUR_NOT_FOUND(180,"No Foournisseur Found"),
    LIGNE_ACHAT_NOT_FOUND(190,"No Ligne Achat Found"),
    LIGNE_VENTE_NOT_FOUND(210,"No Ligne Vente Found"),
    PRODUIT_NOT_FOUND(220,"No Produit Found"),
    TRANSACTION_NOT_FOUND(230,"No Transaction Found"),
    USER_NOT_FOUND(240,"No User Found"),
    VENTE_NOT_FOUND(260,"No Vente Found"),
    VERSEMENT_NOT_FOUND(250,"No Versement Found"),

    OUT_OF_STOCK(510,"You haven't enough stock of this product"),
    OUT_OF_MONEY(520,"You haven't enough money in your balance"),

    HAS_DEBT(530,"Can not delete a partner have debts"),

    NO_CODE(0,"No CODE")
    ;
    private final int code ;
    private HttpStatus httpStatus ;
    private final String description ;
}
