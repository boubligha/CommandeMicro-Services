package com.example.commande_ms1.ws.facade;


import com.example.commande_ms1.entity.Commande;
import com.example.commande_ms1.service.impl.CommandeServiceImpl;
import com.example.commande_ms1.ws.converter.CommandeConverter;
import com.example.commande_ms1.ws.dto.CommandeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/commande/")
public class CommandeWs {
    @Autowired
    CommandeServiceImpl service;
    @Autowired
    CommandeConverter convertor;

    @GetMapping("ref/{ref}")
    public CommandeDto findByRef(@PathVariable String ref) {
        Commande commande = service.findByRef(ref);
        if (commande != null) {
            return convertor.toDto(commande);
        }
        return null;
    }

    @PostMapping("")
    public int save(@RequestBody CommandeDto commandeDto) {
        return service.save(convertor.toEntity(commandeDto));
    }


    @PostMapping("ref/{ref}")
    public int update(@PathVariable String ref, @RequestBody CommandeDto commandeDto) {
        return service.update(ref, convertor.toEntity(commandeDto));
    }

    @DeleteMapping("ref/{ref}")
    public int delete(@PathVariable String ref) {
        return service.deleteByRef(ref);
    }
}

