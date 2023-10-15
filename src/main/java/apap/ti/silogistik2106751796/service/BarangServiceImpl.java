package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.repository.BarangDb;
import apap.ti.silogistik2106751796.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        for (Barang barang : getAllBarang()) {
            if(sku.equals(barang.getSku())) {
                return barang;
            }
        }
        return null;
    }
    @Override
    public void save(Barang barang) {
        barang.setSku(generateSKU(barang));
        barangDb.save(barang);
    }

    @Override
    public String generateSKU(Barang barang){
        String sku = "";

        int tipe = barang.getTipeBarang();
        long skuNumber = barangDb.count()+1;
        String formattedNumber = String.format("%03d", skuNumber);
        if (tipe == 1) {
            sku += "ELEC";
        } else if (tipe == 2) {
            sku += "CLOT";
        } else if (tipe == 3) {
            sku += "FOOD";
        } else if (tipe == 4) {
            sku += "COSM";
        } else {
            sku += "TOOL";
        }

        sku += formattedNumber;

        return sku;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if(barang != null) {
            barang.setMerk(barangFromDto.getMerk());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;

    }

    @Override
    public long countAllBarang() {
        return barangDb.count();
    }
}
