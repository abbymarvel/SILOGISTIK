package apap.ti.silogistik2106751796.repository;

import apap.ti.silogistik2106751796.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface GudangDb extends JpaRepository<Gudang, Long> {
    List<Gudang> findAll();
    Optional<Gudang> findById(long id);
}
