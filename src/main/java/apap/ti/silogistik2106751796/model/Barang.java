package apap.ti.silogistik2106751796.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @Column(name = "merk", nullable = false)
    private String merk;

    @Column(name = "harga_barang", nullable = false)
    private BigDecimal hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengiriramBarang;
}
