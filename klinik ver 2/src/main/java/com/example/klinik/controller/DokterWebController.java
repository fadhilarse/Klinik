package com.example.klinik.controller;

import com.example.klinik.model.Dokter;
import com.example.klinik.repository.DokterRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dokter")
public class DokterWebController {

    @Autowired
    private DokterRepository dokterRepository;

    @GetMapping
    public String tampilkanDokter(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("listDokter", dokterRepository.findByNamaContainingIgnoreCase(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("listDokter", dokterRepository.findAll());
        }

        if (!model.containsAttribute("dokter")) {
            model.addAttribute("dokter", new Dokter());
        }
        return "dokter";
    }

    // -------------------------------------------------------------
    // PROSES SIMPAN / UPDATE DENGAN VALIDASI
    // -------------------------------------------------------------
    @PostMapping("/simpan")
    public String simpanDokter(
            @Valid @ModelAttribute("dokter") Dokter dokter,
            BindingResult bindingResult, // Wajib ditaruh persis setelah @Valid
            Model model,
            RedirectAttributes redirectAttributes) {

        // Jika ada input tidak valid (misal: nama kosong, format hp salah)
        if (bindingResult.hasErrors()) {
            // Sertakan kembali data tabel agar tabel tidak hilang saat halaman di-render
            // balik
            model.addAttribute("listDokter", dokterRepository.findAll());
            return "dokter"; // Kembali ke view tanpa redirect agar pesan error & isian form tidak hilang
        }

        boolean isEdit = (dokter.getId() != null);
        dokterRepository.save(dokter);

        if (isEdit) {
            redirectAttributes.addFlashAttribute("successMessage", "Data dokter berhasil diperbarui!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Dokter baru berhasil ditambahkan!");
        }

        return "redirect:/dokter";
    }

    @GetMapping("/edit/{id}")
    public String editDokter(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Dokter dokter = dokterRepository.findById(id).orElse(null);

        if (dokter == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data dokter tidak ditemukan!");
            return "redirect:/dokter";
        }

        model.addAttribute("dokter", dokter);
        model.addAttribute("listDokter", dokterRepository.findAll());
        return "dokter";
    }

    @GetMapping("/hapus/{id}")
    public String hapusDokter(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (dokterRepository.existsById(id)) {
            dokterRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data dokter berhasil dihapus!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menghapus! Dokter tidak ditemukan.");
        }
        return "redirect:/dokter";
    }
}