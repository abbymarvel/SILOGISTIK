package apap.ti.silogistik2106751796.DTO;

import apap.ti.silogistik2106751796.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.PermintaanPengiriman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman CreatePermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    PermintaanPengiriman UpdatePermintaanPengirimanRequestDTOToPermintaanPengiriman(UpdatePermintaanPengirimanRequestDTO updatePermintaanPengirimanRequestDTO);
}
