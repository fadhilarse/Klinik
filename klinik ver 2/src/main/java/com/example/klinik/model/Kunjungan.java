package com.example.klinik.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Kunjungan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tanggalKunjungan;
    private String keluhan;
    private String status; // Menunggu, Diperiksa, Selesai, Batal

    @ManyToOne
    @JoinColumn(name = "pasien_id")
    private Pasien pasien;

    @ManyToOne
    @JoinColumn(name = "dokter_id")
    private Dokter dokter;

    // --- TAMBAHAN RELASI RUANGAN ---
    @ManyToOne
    @JoinColumn(name = "ruangan_id")
    private Ruangan ruangan;

    public Kunjungan() {
    }

    // --- GETTER & SETTER ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalKunjungan() {
        return tanggalKunjungan;
    }

    public void setTanggalKunjungan(LocalDate tanggalKunjungan) {
        this.tanggalKunjungan = tanggalKunjungan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    // --- GETTER & SETTER RUANGAN ---
    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }
}