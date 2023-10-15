package apap.ti.silogistik2106751796.controller;

import apap.ti.silogistik2106751796.DTO.BarangMapper;
import apap.ti.silogistik2106751796.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751796.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751796.model.Barang;
import apap.ti.silogistik2106751796.model.Gudang;
import apap.ti.silogistik2106751796.model.GudangBarang;
import apap.ti.silogistik2106751796.service.BarangService;
import apap.ti.silogistik2106751796.service.GudangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BarangController {
    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    @GetMapping("barang")
    public String listBarang(Model model) {
        List<ReadBarangResponseDTO> listBarangDTO = new ArrayList<>();
        for (Barang barang: barangService.getAllBarang()) {
            listBarangDTO.add(barangMapper.barangToReadBarangResponseDTO(barang));
        }
        model.addAttribute("listBarang", listBarangDTO);
        return "viewall-barang";
    }

    @GetMapping("barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        var barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        //mappingan buku ke readbuku dto
        model.addAttribute("barangDTO", barangDTO);
        return "view-barang";
    }

    @GetMapping("barang/{idBarang}/ubah")
    public String formUpdateBarang(@PathVariable("idBarang") String idBarang, Model model) {
        var barang = barangService.getBarangBySku(idBarang);
        var barangDTO = barangMapper.barangToUpdatebarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);
        return "form-update-barang";
    }

    @PostMapping("barang/ubah")
    public String updateBuku(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
        var barangFromDto = barangMapper.updatebarangRequestDTOToBarang(barangDTO);
        var barang= barangService.updateBarang(barangFromDto);
        model.addAttribute("barang", barang.getMerk());
        return "success-update-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return  "form-create-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    errors.add(fieldError.getField() + ": " + error.getDefaultMessage());
                } else {
                    errors.add(error.getDefaultMessage());
                }
            }
            model.addAttribute("errors", errors);
            return "error-view-all";
        }
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barangService.save(barang);
        model.addAttribute("barang", barang.getMerk());
        return "success-create-barang";
    }
}