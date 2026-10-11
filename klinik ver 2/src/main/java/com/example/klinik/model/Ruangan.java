package com.example.klinik.model;

import jakarta.persistence.*;

@Entity
public class Ruangan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomorRuang;

    // --- REVERSE ENGINEERING: KOMPOSISI KE KUNJUNGAN ---
    // optional = false memastikan Ruangan tidak bisa dibuat/disimpan
    // tanpa adanya transaksi Kunjungan yang menaunginya
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kunjungan_id", nullable = false)
    private Kunjungan kunjungan;

    public Ruangan() {
    }

    public Ruangan(String nomorRuang, Kunjungan kunjungan) {
        this.nomorRuang = nomorRuang;
        this.kunjungan = kunjungan;
    }

    // --- GETTER & SETTER ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomorRuang() {
        return nomorRuang;
    }

    public void setNomorRuang(String nomorRuang) {
        this.nomorRuang = nomorRuang;
    }

    public Kunjungan getKunjungan() {
        return kunjungan;
    }

    public void setKunjungan(Kunjungan kunjungan) {
        this.kunjungan = kunjungan;
    }
}