package ma.aboulhoda.paimentms.service.impl;

import ma.aboulhoda.paimentms.bean.Paiment;
import ma.aboulhoda.paimentms.dao.PaimentDao;
import ma.aboulhoda.paimentms.service.facade.PaimentService;
import ma.aboulhoda.paimentms.service.requirede.CommandeController; // Add Feign client interface
import ma.aboulhoda.paimentms.ws.dto.CommandeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaimentServiceImpl implements PaimentService {

    @Autowired
    private PaimentDao dao;

    @Autowired
    private CommandeController commandeController; // Inject Feign client

    @Override
    public Paiment findByCode(String code) {
        return dao.findByCode(code);
    }

    private int validateSave(Paiment paiment) {
        if (findByCode(paiment.getCode()) != null) {
            return -1;
        }
        CommandeDto commandeDto = commandeController.findCommandeByRef(paiment.getCommandeRef());

        if (commandeDto == null) {
            return -3; // Commande not found
        }

        BigDecimal totalPaye = commandeDto.getTotalPaye() != null ? commandeDto.getTotalPaye() : BigDecimal.ZERO;
        BigDecimal total = commandeDto.getTotal() != null ? commandeDto.getTotal() : BigDecimal.ZERO;

        if (totalPaye.add(paiment.getMontant()).compareTo(total) > 0) {
            return -2; // Payment amount exceeds the remaining balance
        }

        return 1; // Validation successful
    }

    @Override
    public int save(Paiment paiment) {
        int validationResult = validateSave(paiment);

        if (validationResult > 0) {
            // Fetch the associated commande
            CommandeDto commandeDto = commandeController.findCommandeByRef(paiment.getCommandeRef());

            // Update the total paid amount
            BigDecimal totalPaye = commandeDto.getTotalPaye() != null ? commandeDto.getTotalPaye() : BigDecimal.ZERO;
            commandeDto.setTotalPaye(totalPaye.add(paiment.getMontant()));

            // Update the commande in the external service
            int updateResponse = commandeController.updateCommande(paiment.getCommandeRef(), commandeDto);

            if (updateResponse > 0) {
                // Save the payment
                dao.save(paiment);
                return 1; // Payment saved successfully
            } else {
                return -4; // Failed to update commande
            }
        }

        return validationResult; // Return validation error code
    }
}