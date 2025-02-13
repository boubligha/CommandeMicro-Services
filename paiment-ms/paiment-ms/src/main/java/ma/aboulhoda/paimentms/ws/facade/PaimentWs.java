package ma.aboulhoda.paimentms.ws.facade;

import ma.aboulhoda.paimentms.service.facade.PaimentService;
import ma.aboulhoda.paimentms.ws.convertor.PaimentConvertor;
import ma.aboulhoda.paimentms.ws.dto.PaimentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/paiment/")
public class PaimentWs {

    private final PaimentService service;
    private final PaimentConvertor convertor;

    @Autowired
    public PaimentWs(PaimentService service, PaimentConvertor convertor) {
        this.service = service;
        this.convertor = convertor;
    }

    @PostMapping
    public int save(@RequestBody PaimentDto paimentDto) {
        return service.save(convertor.toEntity(paimentDto));
    }
}
