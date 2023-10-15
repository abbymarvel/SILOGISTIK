package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    List<PermintaanPengiriman> findAll();
    List<PermintaanPengiriman> findAllByIsCancelled(boolean isDeleted);
}
