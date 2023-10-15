package apap.ti.silogistik2106751796.DTO.response;

import apap.ti.silogistik2106751796.model.GudangBarang;
import jakarta.validation.constraints.Min;
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
public class ReadBarangResponseDTO {
    private String sku;
    private String merk;
    private String tipeBarang;
    private BigDecimal hargaBarang;
    private Integer stok;
    private List<GudangBarang> listGudangBarang;

}

