package apap.ti.silogistik2106751796;

import apap.ti.silogistik2106751796.DTO.GudangMapper;
import apap.ti.silogistik2106751796.DTO.KaryawanMapper;
import apap.ti.silogistik2106751796.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751796.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751796.service.GudangService;
import apap.ti.silogistik2106751796.service.KaryawanService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class Silogistik2106751796Application {
	@Autowired
	KaryawanMapper karyawanMapper;

	@Autowired
	KaryawanService karyawanService;

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751796Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run (GudangService gudangService, GudangMapper gudangMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));


			var gudangDTO = new CreateGudangRequestDTO();

			var namaGudang = "Sortir" + faker.company().name();
			var alamatGudang = faker.address().streetAddress();

			gudangDTO.setNama(namaGudang);
			gudangDTO.setAlamatGudang(alamatGudang);

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);

			gudangService.createGudang(gudang);

			var karyawanDTO = new CreateKaryawanRequestDTO();

			var namaKaryawan = faker.name().fullName() + " Tohir";
			var tanggalLahir = faker.date().birthday(35, 40);

			karyawanDTO.setNama(namaKaryawan);
			karyawanDTO.setTanggalLahir(tanggalLahir);
			karyawanDTO.setJenisKelamin(1);


			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);

			karyawanService.createKaryawan(karyawan);
		};
	}
}
