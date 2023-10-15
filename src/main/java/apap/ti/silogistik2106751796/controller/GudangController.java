package apap.ti.silogistik2106751796.controller;

import apap.ti.silogistik2106751796.DTO.GudangMapper;
import apap.ti.silogistik2106751796.DTO.request.*;
import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.GudangBarang;
import apap.ti.silogistik2106751796.repository.BarangDb;
import apap.ti.silogistik2106751796.repository.GudangBarangDb;
import apap.ti.silogistik2106751796.repository.GudangDb;
import apap.ti.silogistik2106751796.service.BarangService;
import apap.ti.silogistik2106751796.service.GudangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    BarangDb barangDb;

    @GetMapping("gudang")
    public String listGudang(Model model) {
        var listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping("gudang/{id}")
    public String detailGudang(@PathVariable("id") long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        //mappingan buku ke readbuku dto
        model.addAttribute("gudang", gudang);
        return "view-gudang";
    }

    @GetMapping("gudang/{id}/restock-barang")
    public String showRestockPage(@PathVariable("id") long id, Model model) {
        Gudang gudang = gudangService.getGudangById(id);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", gudangDTO);
        return "restock-gudang";
    }


    @PostMapping(value = "gudang/restock-barang", params = {"addRow"})
    public String addRowBarang(@ModelAttribute UpdateGudangRequestDTO restockRequestDTO, Model model) {

        if (restockRequestDTO.getListGudangBarang() == null || restockRequestDTO.getListGudangBarang().size() == 0) {
            restockRequestDTO.setListGudangBarang(new ArrayList<>());
        }

        GudangBarang newGudangBarang = new GudangBarang();

        restockRequestDTO.getListGudangBarang().add(newGudangBarang);

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", restockRequestDTO);

        return "restock-gudang";
    }

    @PostMapping("gudang/restock-barang")
    public String updateStock(@Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO, BindingResult bindingResult, Model model) {
        var gudangFromDto = gudangMapper.UpdateGudangRequestDTOToGudang(gudangDTO);
        var gudang = gudangService.updateStock(gudangFromDto);
        model.addAttribute("gudang", gudang.getNama());
        return "success-restock";

    }

    @GetMapping("gudang/cari-barang")
    public String cariBarang(@RequestParam(name = "sku", required = false) String sku, Model model) {
        List<Barang> listBarang = barangService.getAllBarang();

        if(sku != null && !sku.isEmpty()){
            List<GudangBarang> gudangBarangList = barangService.getBarangBySku(sku).getListGudangBarang();
            model.addAttribute("sku", sku);
            model.addAttribute("gudangBarangList", gudangBarangList);
        }

        model.addAttribute("listBarang", listBarang);
        return "view-cari-barang";
    }
}
