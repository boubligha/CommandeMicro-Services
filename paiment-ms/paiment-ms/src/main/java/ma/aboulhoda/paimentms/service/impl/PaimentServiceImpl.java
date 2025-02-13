package ma.aboulhoda.paimentms.service.impl;


import ma.aboulhoda.paimentms.bean.Paiment;
import ma.aboulhoda.paimentms.dao.PaimentDao;
import ma.aboulhoda.paimentms.service.facade.PaimentService;
import ma.aboulhoda.paimentms.ws.dto.CommandeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaimentServiceImpl implements PaimentService {

    private final PaimentDao dao;
    private final RestTemplate restTemplate;
    @Value("${app.api.commande}")
    private String commandeBaseUrl;

    @Autowired
    public PaimentServiceImpl(PaimentDao dao, RestTemplate restTemplate) {
        this.dao = dao;
        this.restTemplate = restTemplate;
    }


    @Override
    public Paiment findByCode(String code) {
        return dao.findByCode(code);
    }

    private int validateSave(Paiment paiment) {
        if (findByCode(paiment.getCode()) != null) return -1;

        CommandeDto commandeDto = findCommandeByRef(paiment.getCommandeRef());

        if (commandeDto.getTotalPaye().add(paiment.getMontant()).compareTo(commandeDto.getTotal()) > 0) return -2;

        return 1;
    }

    @Override
    public int save(Paiment paiment) {
        int result = validateSave(paiment);
        if (result > 0) {
            CommandeDto commandeDto = findCommandeByRef(paiment.getCommandeRef());
            commandeDto.setTotalPaye(paiment.getMontant().add(commandeDto.getTotalPaye()));

            int response = updateCommande(commandeDto);

            if (response > 0) {
                dao.save(paiment);
            }
            return response;
        }
        return result;
    }

    private CommandeDto findCommandeByRef(String ref) {
        String url = commandeBaseUrl + "/ref/" + ref;
        return restTemplate.getForEntity(url, CommandeDto.class).getBody();
    }

    private int updateCommande(CommandeDto commandeDto) {
        String url = commandeBaseUrl + "/ref/" + commandeDto.getRef();
        return restTemplate.postForEntity(url, commandeDto, Integer.class).getBody();
    }
}
