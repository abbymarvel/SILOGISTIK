package apap.ti.silogistik2106751796.DTO.request;

import apap.ti.silogistik2106751796.model.GudangBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBarangGudangRequestDTO extends CreateBarangGudangRequestDTO{
    @NotNull
    private Integer stok;
}
