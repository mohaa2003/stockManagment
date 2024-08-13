package com.stockManagment.api.produit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepo extends JpaRepository<Produit,Integer> {
}
