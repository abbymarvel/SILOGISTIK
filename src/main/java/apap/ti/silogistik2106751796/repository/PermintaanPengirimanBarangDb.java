package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.PermintaanPengirimanBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    List<PermintaanPengirimanBarang> findAll();
}
