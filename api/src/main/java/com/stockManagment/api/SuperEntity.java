package com.stockManagment.api;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
public class SuperEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
