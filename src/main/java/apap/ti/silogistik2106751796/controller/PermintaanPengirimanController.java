package apap.ti.silogistik2106751796.controller;

import apap.ti.silogistik2106751796.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751796.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751796.model.GudangBarang;
import apap.ti.silogistik2106751796.model.Karyawan;
import apap.ti.silogistik2106751796.model.PermintaanPengiriman;
import apap.ti.silogistik2106751796.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751796.repository.GudangBarangDb;
import apap.ti.silogistik2106751796.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106751796.service.BarangService;
import apap.ti.silogistik2106751796.service.GudangService;
import apap.ti.silogistik2106751796.service.KaryawanService;
import apap.ti.silogistik2106751796.service.PermintaanPengirimanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangService gudangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @GetMapping("permintaan-pengiriman")
    public String viewAll(Model model) {
        var permintaanPengirimanList = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("permintaanPengirimanList", permintaanPengirimanList);
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{id}")
    public String detailPermintaanPengiriman(@PathVariable("id") long id, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        return "form-create-permintaan-pengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {

        if (createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang() == null || createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().size() == 0) {
            createPermintaanPengirimanRequestDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        PermintaanPengirimanBarang permintaanPengiriman = new PermintaanPengirimanBarang();
        createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().add(permintaanPengiriman);
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("idKaryawan", createPermintaanPengirimanRequestDTO.getKaryawan().getId());
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);

        return "form-create-permintaan-pengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String updatePermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, BindingResult bindingResult, Model model) {
        var permintaanPengirimanFromDTO = permintaanPengirimanService.transform(createPermintaanPengirimanRequestDTO);
        permintaanPengirimanService.save(permintaanPengirimanFromDTO);
        model.addAttribute("permintaanPengiriman", permintaanPengirimanFromDTO.getNomorPengiriman());
        return "success-create-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{id}/cancel")
    public String deletePermintaanPengiriman(@PathVariable("id") long id, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Get the waktu_permintaan of the PermintaanPengiriman object
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();

        // Check if waktu_permintaan is more than 24 hours ago
        if (waktuPermintaan.isBefore(now.minusHours(24))) {
            // If it is, return an error message or throw an exception
            model.addAttribute("error", "Cannot delete PermintaanPengiriman more than 24 hours ago");
            return "failed-delete-permintaan-pengiriman";
        } else {
            // If it's not, proceed with the deletion
            permintaanPengirimanService.deletePermintaanPengiriman(permintaanPengiriman);

            model.addAttribute("id", permintaanPengiriman.getNomorPengiriman());
            return "success-delete-permintaan-pengiriman";
        }
    }

    @GetMapping("bonus")
    public String viewBonus(Model model) {
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        return "bonus";
    }

    @GetMapping(value = "bonus", params = {"start_date", "end_date", "sku"})
    public String filterPermintaanPengiriman(
            @RequestParam(value = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "sku") String SKU,
            Model model) {

        List<PermintaanPengiriman> filteredList = permintaanPengirimanService.getAllPermintaanPengiriman()
                .stream()
                .filter(permintaanPengiriman -> {
                    LocalDate waktuPermintaan = permintaanPengiriman.getWaktuPermintaan().toLocalDate();
                    return !waktuPermintaan.isBefore(startDate) && !waktuPermintaan.isAfter(endDate) &&
                            permintaanPengiriman.getListPermintaanPengirimanBarang().stream()
                                    .anyMatch(barang -> barang.getBarang().getSku().equals(SKU));
                })
                .collect(Collectors.toList());

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listPermintaanPengiriman", filteredList);

        return "bonus";
    }
}
