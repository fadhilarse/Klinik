package com.example.klinik.controller;

import com.example.klinik.model.Kunjungan;
import com.example.klinik.model.RekamMedis;
import com.example.klinik.repository.KunjunganRepository;
import com.example.klinik.repository.RekamMedisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/pemeriksaan")
public class PemeriksaanController {

    @Autowired
    private KunjunganRepository kunjunganRepository;

    @Autowired
    private RekamMedisRepository rekamMedisRepository;

    // 1. Menampilkan Halaman Daftar Antrean (HANYA STATUS 'MENUNGGU')
    @GetMapping
    public String halamanPemeriksaan(Model model) {
        model.addAttribute("antreanDokter", kunjunganRepository.findByStatusIgnoreCase("MENUNGGU"));
        return "pemeriksaan";
    }

    // 2. Menampilkan Form Pemeriksaan + Riwayat Medis Lalu Pasien
    @GetMapping("/proses/{id}")
    public String formPemeriksaan(@PathVariable("id") Long id, Model model) {
        Kunjungan kunjungan = kunjunganRepository.findById(id).orElse(null);

        if (kunjungan == null) {
            return "redirect:/pemeriksaan";
        }

        RekamMedis rekamMedis = new RekamMedis();
        rekamMedis.setKunjungan(kunjungan);

        // Ambil riwayat medis terdahulu milik pasien ini
        if (kunjungan.getPasien() != null) {
            model.addAttribute("riwayatLalu",
                    rekamMedisRepository
                            .findByKunjunganPasienIdOrderByWaktuPemeriksaanDesc(kunjungan.getPasien().getId()));
        }

        model.addAttribute("kunjungan", kunjungan);
        model.addAttribute("rekamMedis", rekamMedis);

        return "pemeriksaan-form";
    }

    // 3. Menyimpan Data Rekam Medis ke Database
    @PostMapping("/simpan")
    public String simpanPemeriksaan(@ModelAttribute RekamMedis rekamMedis, RedirectAttributes redirectAttributes) {

        // Otomatis isi waktuPemeriksaan dengan tanggal & jam saat tombol disimpan
        rekamMedis.setWaktuPemeriksaan(LocalDateTime.now());
        rekamMedisRepository.save(rekamMedis);

        Kunjungan kunjungan = kunjunganRepository.findById(rekamMedis.getKunjungan().getId()).orElse(null);
        if (kunjungan != null) {
            kunjungan.setStatus("SELESAI");
            kunjunganRepository.save(kunjungan);
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Pemeriksaan selesai dan rekam medis berhasil disimpan!");
        return "redirect:/pemeriksaan";
    }

    // 4. Halaman Global: Melihat Seluruh Riwayat Medis Klinik (Untuk Menu Sidebar)
    @GetMapping("/riwayat")
    public String semuaRiwayatMedis(Model model) {
        model.addAttribute("daftarRiwayat", rekamMedisRepository.findAll());
        return "riwayat-medis";
    }

    // 5. Halaman Spesifik: Melihat Riwayat Rekam Medis Pasien Tertentu
    @GetMapping("/riwayat/{pasienId}")
    public String halamanRiwayatPasien(@PathVariable("pasienId") Long pasienId, Model model) {
        model.addAttribute("daftarRiwayat",
                rekamMedisRepository.findByKunjunganPasienIdOrderByWaktuPemeriksaanDesc(pasienId));
        return "riwayat-medis";
    }

    // 6. TAMBAHAN: Menghapus Data Rekam Medis
    @GetMapping("/riwayat/hapus/{id}")
    public String hapusRiwayat(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            rekamMedisRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data riwayat medis berhasil dihapus!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menghapus data riwayat medis.");
        }
        return "redirect:/pemeriksaan/riwayat";
    }
}