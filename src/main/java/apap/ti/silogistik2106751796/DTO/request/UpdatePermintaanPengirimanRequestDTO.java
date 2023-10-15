package apap.ti.silogistik2106751796.DTO.request;

import apap.ti.silogistik2106751796.model.Karyawan;
import apap.ti.silogistik2106751796.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePermintaanPengirimanRequestDTO extends CreatePermintaanPengirimanRequestDTO{
    private Long id;
}
