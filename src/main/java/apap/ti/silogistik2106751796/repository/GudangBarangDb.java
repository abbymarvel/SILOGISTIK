package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findAll();
    List<GudangBarang> findAllByGudang_Id(BigDecimal gudangId);
}