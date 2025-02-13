package ma.aboulhoda.paimentms.service.requirede;

import ma.aboulhoda.paimentms.ws.dto.CommandeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "commande", url = "http://localhost:8080/api/commande")
public interface CommandeController {

    @RequestMapping(method = RequestMethod.GET, value = "/ref/{ref}")
    CommandeDto findCommandeByRef(@PathVariable String ref);

    @RequestMapping(method = RequestMethod.POST, value = "/ref/{ref}")
    int updateCommande(@PathVariable String ref, @RequestBody CommandeDto commandeDto);
}