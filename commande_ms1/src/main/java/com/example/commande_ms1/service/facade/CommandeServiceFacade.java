package com.example.commande_ms1.service.facade;

import com.example.commande_ms1.entity.Commande;

public interface CommandeServiceFacade {
    Commande findByRef(String ref);

    int update(String ref, Commande commande);

    int deleteByRef(String ref);

}
