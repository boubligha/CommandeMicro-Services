package com.example.commande_ms1.ws.converter;

import com.example.commande_ms1.entity.Commande;
import com.example.commande_ms1.ws.dto.CommandeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CommandeConverter {

    public Commande toEntity(CommandeDto commandeDto) {
        Commande commande = new Commande();
        BeanUtils.copyProperties(commandeDto, commande);
        return commande;
    }

    public CommandeDto toDto(Commande commande) {
        CommandeDto commandeDto = new CommandeDto();
        BeanUtils.copyProperties(commande, commandeDto);
        return commandeDto;
    }

}
