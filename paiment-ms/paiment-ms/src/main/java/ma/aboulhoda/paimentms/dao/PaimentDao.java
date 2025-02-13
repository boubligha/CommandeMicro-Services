package ma.aboulhoda.paimentms.dao;

import ma.aboulhoda.paimentms.bean.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentDao extends JpaRepository<Paiment, Long> {

    Paiment findByCode(String code);

}
