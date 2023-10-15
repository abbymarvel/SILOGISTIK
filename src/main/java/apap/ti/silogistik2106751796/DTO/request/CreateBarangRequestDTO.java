package apap.ti.silogistik2106751796.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    @NotNull
    private Integer tipeBarang;
    @NotBlank
    private String merk;
    @Min(0L)
    private BigDecimal hargaBarang;
}
