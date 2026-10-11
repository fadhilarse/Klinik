package com.example.klinik.controller;

import com.example.klinik.repository.DokterRepository;
import com.example.klinik.repository.KunjunganRepository;
import com.example.klinik.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private KunjunganRepository kunjunganRepository;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalDokter", dokterRepository.count());
        model.addAttribute("totalPasien", pasienRepository.count());
        model.addAttribute("antreanHariIni", kunjunganRepository.findByStatus("Menunggu").size());
        model.addAttribute("pemeriksaanSelesai", kunjunganRepository.findByStatus("Selesai").size());
        return "dashboard"; // Sesuaikan dengan nama file HTML Anda (dashboard atau index)
    }
}