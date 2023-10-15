package apap.ti.silogistik2106751796.DTO.request;

import apap.ti.silogistik2106751796.model.GudangBarang;
import apap.ti.silogistik2106751796.model.Karyawan;
import apap.ti.silogistik2106751796.model.PermintaanPengirimanBarang;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private String nomorPengiriman;
    private Boolean isCancelled;
    private String namaPenerima;
    private String alamatPenerima;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;
    private Integer biayaPengiriman;
    private Integer jenisLayanan;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}
