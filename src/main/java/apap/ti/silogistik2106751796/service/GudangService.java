package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751796.model.Gudang;

import java.math.BigDecimal;
import java.util.List;

public interface GudangService {
    Gudang createGudang(Gudang gudang);
    List<Gudang> getAllGudang();
    Gudang getGudangById(long id);

    Gudang updateStock(Gudang gudangFromDTO);

    long countAllGudang();
}
