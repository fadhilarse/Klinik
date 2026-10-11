package com.example.klinik.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klinik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaKlinik;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "klinik_id")
    private List<Ruangan> daftarRuangan = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "klinik_id")
    private List<Dokter> daftarDokter = new ArrayList<>();

    public Klinik() {
    }

    public Klinik(String namaKlinik) {
        this.namaKlinik = namaKlinik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKlinik() {
        return namaKlinik;
    }

    public void setNamaKlinik(String namaKlinik) {
        this.namaKlinik = namaKlinik;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void setDaftarRuangan(List<Ruangan> daftarRuangan) {
        this.daftarRuangan = daftarRuangan;
    }

    public void addRuangan(Ruangan r) {
        this.daftarRuangan.add(r);
    }

    public List<Dokter> getDaftarDokter() {
        return daftarDokter;
    }

    public void setDaftarDokter(List<Dokter> daftarDokter) {
        this.daftarDokter = daftarDokter;
    }

    public void addDokter(Dokter d) {
        this.daftarDokter.add(d);
    }
}