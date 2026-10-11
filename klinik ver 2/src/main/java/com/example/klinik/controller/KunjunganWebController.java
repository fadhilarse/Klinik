package com.example.klinik.controller;

import com.example.klinik.model.Kunjungan;
import com.example.klinik.repository.DokterRepository;
import com.example.klinik.repository.KunjunganRepository;
import com.example.klinik.repository.PasienRepository;
import com.example.klinik.repository.RuanganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; // <-- Tambahan Import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class KunjunganWebController {

    @Autowired
    private KunjunganRepository kunjunganRepository;

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private RuanganRepository ruanganRepository;

    // --- ALUR ADMIN: Pendaftaran Kunjungan/Antrean ---
    @GetMapping("/kunjungan")
    public String halamanKunjungan(Model model) {
        if (!model.containsAttribute("kunjungan")) {
            model.addAttribute("kunjungan", new Kunjungan());
        }
        model.addAttribute("listPasien", pasienRepository.findAll());
        model.addAttribute("listDokter", dokterRepository.findAll());
        model.addAttribute("listRuangan", ruanganRepository.findAll());

        model.addAttribute("listKunjungan", kunjunganRepository.findAll());
        return "kunjungan";
    }

    @PostMapping("/kunjungan/simpan")
    public String simpanKunjungan(@ModelAttribute("kunjungan") Kunjungan kunjungan,
            RedirectAttributes redirectAttributes) {
        if (kunjungan.getTanggalKunjungan() == null) {
            kunjungan.setTanggalKunjungan(LocalDate.now());
        }
        if (kunjungan.getStatus() == null || kunjungan.getStatus().isEmpty()) {
            kunjungan.setStatus("Menunggu");
        }
        kunjunganRepository.save(kunjungan);
        redirectAttributes.addFlashAttribute("successMessage", "Antrean berhasil disimpan!");
        return "redirect:/kunjungan";
    }

    // --- TAMBAHAN: Fungsi Edit dan Hapus Kunjungan ---
    @GetMapping("/kunjungan/edit/{id}")
    public String editKunjungan(@PathVariable Long id, Model model) {
        Kunjungan kunjungan = kunjunganRepository.findById(id).orElse(new Kunjungan());
        model.addAttribute("kunjungan", kunjungan);
        model.addAttribute("listPasien", pasienRepository.findAll());
        model.addAttribute("listDokter", dokterRepository.findAll());
        model.addAttribute("listRuangan", ruanganRepository.findAll());

        model.addAttribute("listKunjungan", kunjunganRepository.findAll());
        return "kunjungan";
    }

    @GetMapping("/kunjungan/hapus/{id}")
    public String hapusKunjungan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            kunjunganRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data antrean berhasil dihapus!");
        } catch (DataIntegrityViolationException e) {
            // <-- Menangkap error Relasi (Foreign Key)
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Gagal dihapus: Antrean tidak bisa dihapus karena pasien sudah diperiksa dan masuk ke Rekam Medis!");
        }
        return "redirect:/kunjungan";
    }
}