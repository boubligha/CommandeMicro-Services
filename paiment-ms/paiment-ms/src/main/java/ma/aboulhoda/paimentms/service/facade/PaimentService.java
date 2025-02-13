package ma.aboulhoda.paimentms.service.facade;

import ma.aboulhoda.paimentms.bean.Paiment;

public interface PaimentService {

    Paiment findByCode(String ref);

    int save(Paiment paiment);

}
