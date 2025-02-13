package com.example.commande_ms1.service.impl;


import com.example.commande_ms1.dao.CommandeDao;
import com.example.commande_ms1.entity.Commande;
import com.example.commande_ms1.service.facade.CommandeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommandeServiceImpl implements CommandeServiceFacade {

    @Autowired
    private CommandeDao dao;

    @Override
    public Commande findByRef(String ref) {
        return dao.findByRef(ref);
    }

    private int validateUpdate(String ref) {
        if (findByRef(ref) == null) return -1;
        return 1;
    }

    public int save(Commande commande){
        if(findByRef(commande.getRef())!=null){
            return -1;
        }else {
            dao.save(commande);
            return 1;
        }
    }

    @Override
    public int update(String ref, Commande commande) {
        int result = validateUpdate(ref);
        if (result > 0) {
            Commande existingCommande = findByRef(commande.getRef());
            existingCommande.setTotalPaye(commande.getTotalPaye());
            dao.save(existingCommande);
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        return dao.deleteByRef(ref);
    }
}
