package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
    List<Karyawan> findAll();
}
