package apap.ti.silogistik2106751796.DTO;

import apap.ti.silogistik2106751796.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751796.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}