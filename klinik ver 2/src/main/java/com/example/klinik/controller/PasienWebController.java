package com.example.klinik.controller;

import com.example.klinik.model.Pasien;
import com.example.klinik.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasienWebController {

    @Autowired
    private PasienRepository pasienRepository;

    @GetMapping("/pasien")
    public String tampilkanPasien(Model model) {
        model.addAttribute("listPasien", pasienRepository.findAll());
        model.addAttribute("pasien", new Pasien());
        return "pasien";
    }

    @PostMapping("/pasien/simpan")
    public String simpanPasien(@ModelAttribute("pasien") Pasien pasien) {
        pasienRepository.save(pasien);
        return "redirect:/pasien";
    }

    @GetMapping("/pasien/edit/{id}")
    public String editPasien(@PathVariable Long id, Model model) {
        model.addAttribute("listPasien", pasienRepository.findAll());
        model.addAttribute("pasien", pasienRepository.findById(id).orElse(new Pasien()));
        return "pasien";
    }

    @GetMapping("/pasien/hapus/{id}")
    public String hapusPasien(@PathVariable Long id) {
        pasienRepository.deleteById(id);
        return "redirect:/pasien";
    }
}