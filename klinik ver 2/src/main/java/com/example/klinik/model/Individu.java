package com.example.klinik.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Individu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String alamat;

    // 1. Constructor Kosong (WAJIB untuk JPA/Hibernate)
    public Individu() {
    }

    // 2. Constructor Parameter Nama (INI YANG KURANG)
    public Individu(String nama) {
        this.nama = nama;
    }

    // --- GETTER & SETTER ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}