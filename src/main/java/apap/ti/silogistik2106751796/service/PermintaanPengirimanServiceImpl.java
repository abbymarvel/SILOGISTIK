package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751796.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.model.PermintaanPengiriman;
import apap.ti.silogistik2106751796.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751796.repository.BarangDb;
import apap.ti.silogistik2106751796.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106751796.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Override
    public void save(PermintaanPengiriman permintaanPengiriman) {
        List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();

        for(PermintaanPengirimanBarang permintaanPengirimanBarang: permintaanPengiriman.getListPermintaanPengirimanBarang()){
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
            listPermintaanPengirimanBarang.add(permintaanPengirimanBarang);
        }
        permintaanPengiriman.setListPermintaanPengirimanBarang(listPermintaanPengirimanBarang);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAllByIsCancelled(false);
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(long id) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (id == permintaanPengiriman.getId()) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public PermintaanPengiriman transform(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO){
        createPermintaanPengirimanRequestDTO.setIsCancelled(false);
        createPermintaanPengirimanRequestDTO.setWaktuPermintaan(LocalDateTime.now());
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanMapper.CreatePermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);
        permintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(permintaanPengiriman));
        return permintaanPengiriman;
    }

    @Override
    public PermintaanPengiriman updateList(PermintaanPengiriman permintaanPengirimanDTO) {
        PermintaanPengiriman permintaanPengiriman = getPermintaanPengirimanById(permintaanPengirimanDTO.getId());

        if (permintaanPengiriman != null) {
            permintaanPengiriman.setListPermintaanPengirimanBarang(new ArrayList<>());
            for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
                permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
                permintaanPengiriman.getListPermintaanPengirimanBarang().add(permintaanPengirimanBarang);
            }

        }
        permintaanPengirimanDb.save(permintaanPengiriman);
        return permintaanPengiriman;
    }

    @Override
    public String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman){
        String nomorPengiriman = "REQ";

        int quantity=0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang: permintaanPengiriman.getListPermintaanPengirimanBarang()){
            quantity+=permintaanPengirimanBarang.getKuantitasPesanan();
        }
        int formattedQuantity = quantity % 100;

        nomorPengiriman+=String.format("%02d", formattedQuantity);

        int tipe = permintaanPengiriman.getJenisLayanan();
        if (tipe == 1) {
            nomorPengiriman += "SAM";
        } else if (tipe == 2) {
            nomorPengiriman += "KIL";
        } else if (tipe == 3) {
            nomorPengiriman += "REG";
        } else{
            nomorPengiriman += "HEM";
        }

        nomorPengiriman+=permintaanPengiriman.getWaktuPermintaan().toString().substring(11,19);

        return nomorPengiriman;
    }

    @Override
    public long countAllPermintaanPengiriman() {
        return permintaanPengirimanDb.count();
    }

    @Override
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman){
        permintaanPengiriman.setIsCancelled(true);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }
}
