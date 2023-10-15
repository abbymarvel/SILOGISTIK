package apap.ti.silogistik2106751796.controller;

import apap.ti.silogistik2106751796.repository.GudangBarangDb;
import apap.ti.silogistik2106751796.service.BarangService;
import apap.ti.silogistik2106751796.service.GudangService;
import apap.ti.silogistik2106751796.service.KaryawanService;
import apap.ti.silogistik2106751796.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/")
    public String home(Model model) {
        long countGudang = gudangService.countAllGudang();
        long countBarang = barangService.countAllBarang();
        long countPermintaanPengiriman = permintaanPengirimanService.countAllPermintaanPengiriman();
        long countKaryawan = karyawanService.countAllKaryawan();

        model.addAttribute("countGudang", countGudang);
        model.addAttribute("countBarang", countBarang);
        model.addAttribute("countPermintaanPengiriman", countPermintaanPengiriman);
        model.addAttribute("countKaryawan", countKaryawan);
        return "home";
    }
}
