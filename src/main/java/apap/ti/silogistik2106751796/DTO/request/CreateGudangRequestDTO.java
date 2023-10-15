package apap.ti.silogistik2106751796.DTO.request;

import apap.ti.silogistik2106751796.model.GudangBarang;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    @NotBlank
    private String nama;
    @NotBlank
    private String alamatGudang;
    private List<GudangBarang> listGudangBarang;
}