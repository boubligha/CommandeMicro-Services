package ma.aboulhoda.paimentms.ws.convertor;

import ma.aboulhoda.paimentms.bean.Paiment;
import ma.aboulhoda.paimentms.ws.dto.PaimentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaimentConvertor {

    public Paiment toEntity(PaimentDto paimentDto) {
        Paiment paiment = new Paiment();
        BeanUtils.copyProperties(paimentDto, paiment);
        return paiment;
    }

    public PaimentDto toDto(Paiment paiment) {
        PaimentDto paimentDto = new PaimentDto();
        BeanUtils.copyProperties(paiment, paimentDto);
        return paimentDto;
    }

}
