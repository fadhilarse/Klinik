package com.example.klinik.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dokter")
public class Dokter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama dokter tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama harus antara 3 - 100 karakter")
    private String nama;

    @NotBlank(message = "Spesialisasi wajib diisi")
    private String spesialisasi;

    @NotBlank(message = "Nomor Izin Praktek wajib diisi")
    private String nomorIzinPraktek; // Disesuaikan dengan th:field="*{nomorIzinPraktek}"

    @NotBlank(message = "Alamat wajib diisi")
    private String alamat; // Disesuaikan dengan th:field="*{alamat}"

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

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getNomorIzinPraktek() {
        return nomorIzinPraktek;
    }

    public void setNomorIzinPraktek(String nomorIzinPraktek) {
        this.nomorIzinPraktek = nomorIzinPraktek;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}