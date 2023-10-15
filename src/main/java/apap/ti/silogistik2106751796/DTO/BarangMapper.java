package apap.ti.silogistik2106751796.DTO;

import apap.ti.silogistik2106751796.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751796.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.model.GudangBarang;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    UpdateBarangRequestDTO barangToUpdatebarangRequestDTO(Barang barang);
    Barang updatebarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);


    @Mapping(target = "stok", ignore = true)
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);
    @AfterMapping
    default void stokBarang(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO){
        int countStock = 0;
        for(GudangBarang gudangBarang: barang.getListGudangBarang()){
            if(gudangBarang.getBarang().getSku().equals(barang.getSku())){
                countStock+=gudangBarang.getStok();
            }
        }
        readBarangResponseDTO.setStok(countStock);
    }
}