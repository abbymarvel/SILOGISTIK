package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.DTO.GudangMapper;
import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.GudangBarang;
import apap.ti.silogistik2106751796.repository.BarangDb;
import apap.ti.silogistik2106751796.repository.GudangBarangDb;
import apap.ti.silogistik2106751796.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    BarangService barangService;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangMapper gudangMapper;

    @Override
    public Gudang createGudang(Gudang gudang) {
        return gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(long id) {
        for (Gudang gudang : getAllGudang()) {
            if (id == gudang.getId()) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public Gudang updateStock(Gudang gudangDTO) {
        Gudang gudang = getGudangById(gudangDTO.getId());

        if (gudang != null) {
            for (GudangBarang gudangBarangDTO : gudangDTO.getListGudangBarang()) {
                GudangBarang existingGudangBarang = gudang.getListGudangBarang().stream()
                        .filter(gudangBarang -> gudangBarang.getBarang().getSku().equals(gudangBarangDTO.getBarang().getSku()))
                        .findFirst()
                        .orElse(null);

                if (existingGudangBarang != null) {
                    existingGudangBarang.setStok(existingGudangBarang.getStok() + gudangBarangDTO.getStok());
                } else {
                    gudangBarangDTO.setGudang(gudang);
                    gudang.getListGudangBarang().add(gudangBarangDTO);
                }
            }
        }
        gudangDb.save(gudang);
        return gudang;
    }

    @Override
    public long countAllGudang() {
        return gudangDb.count();
    }
}
