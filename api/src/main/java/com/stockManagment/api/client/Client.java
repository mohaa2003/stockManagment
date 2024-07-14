package com.stockManagment.api.client;

import com.stockManagment.api.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client" )
public class Client  extends SuperEntity {
    private String nomClient;
    private String surnomClient;
//    date de 1er achat ... et de dernier achat
}
