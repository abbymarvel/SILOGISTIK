package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface BarangDb extends JpaRepository<Barang, Long> {
    List<Barang> findAll();
    Optional<Barang> findBySkuIgnoreCase(String sku);
}
