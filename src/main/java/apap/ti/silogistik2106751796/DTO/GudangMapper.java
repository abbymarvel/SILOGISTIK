package apap.ti.silogistik2106751796.DTO;

import apap.ti.silogistik2106751796.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751796.model.Gudang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);
    Gudang UpdateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);
}