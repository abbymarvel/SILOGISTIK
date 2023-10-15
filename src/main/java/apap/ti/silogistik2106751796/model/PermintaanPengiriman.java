package apap.ti.silogistik2106751796.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nomor_pengiriman", nullable = false, length = 16)
    private String nomorPengiriman;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan", nullable = false)
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
