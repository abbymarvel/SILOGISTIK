package apap.ti.silogistik2106751796.service;

import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.Karyawan;

import java.util.List;

public interface KaryawanService {

    Karyawan createKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();

    long countAllKaryawan();
}
