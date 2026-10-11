package com.example.klinik.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rekam_medis")
public class RekamMedis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kunjungan_id")
    private Kunjungan kunjungan;

    private String keluhan;
    private String diagnosa;
    private String tindakan; // <-- INI YANG BARU DITAMBAHKAN
    private String resepObat;
    private LocalDateTime waktuPemeriksaan;

    public RekamMedis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kunjungan getKunjungan() {
        return kunjungan;
    }

    public void setKunjungan(Kunjungan kunjungan) {
        this.kunjungan = kunjungan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    // <-- GETTER DAN SETTER TINDAKAN BARU DITAMBAHKAN
    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }
    // ---------------------------------------------

    public String getResepObat() {
        return resepObat;
    }

    public void setResepObat(String resepObat) {
        this.resepObat = resepObat;
    }

    public LocalDateTime getWaktuPemeriksaan() {
        return waktuPemeriksaan;
    }

    public void setWaktuPemeriksaan(LocalDateTime waktuPemeriksaan) {
        this.waktuPemeriksaan = waktuPemeriksaan;
    }
}