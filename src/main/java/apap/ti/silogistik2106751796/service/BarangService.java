package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BarangService {
    List<Barang> getAllBarang();

    Barang getBarangBySku(String sku);

    void save(Barang barang);
    String generateSKU(Barang barang);

    Barang updateBarang(Barang barangFromDto);

    long countAllBarang();
}
