package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.model.PermintaanPengiriman;

import java.util.List;

public interface PermintaanPengirimanService {
    void save(PermintaanPengiriman permintaanPengiriman);

    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(long id);

    PermintaanPengiriman transform(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    PermintaanPengiriman updateList(PermintaanPengiriman permintaanPengirimanDTO);

    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);

    long countAllPermintaanPengiriman();

    void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
