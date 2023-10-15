package apap.ti.silogistik2106751796.DTO.request;

import apap.ti.silogistik2106751796.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751796.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    private Long id;
}
