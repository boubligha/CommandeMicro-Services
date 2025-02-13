package com.example.commande_ms1.dao;


import com.example.commande_ms1.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Long> {


    Commande findByRef(String ref);
    int deleteByRef(String ref);

    Commande save(Commande commande);
}
