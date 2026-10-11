package com.example.klinik.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Ruangan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomorRuang;

    // Perbaikan: Relasi dibalik menjadi OneToMany.
    // mappedBy = "ruangan" merujuk pada nama variabel di Kunjungan.java
    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY)
    private List<Kunjungan> daftarKunjungan;

    public Ruangan() {
    }

    public Ruangan(String nomorRuang) {
        this.nomorRuang = nomorRuang;
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

    public List<Kunjungan> getDaftarKunjungan() {
        return daftarKunjungan;
    }

    public void setDaftarKunjungan(List<Kunjungan> daftarKunjungan) {
        this.daftarKunjungan = daftarKunjungan;
    }
}