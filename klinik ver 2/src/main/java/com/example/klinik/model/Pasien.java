package com.example.klinik.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pasien extends Individu {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pasien_id")
    private List<RekamMedis> daftarRekamMedis = new ArrayList<>();

    public Pasien() {
        super();
    }

    public Pasien(String nama) {
        super(nama);
    }

    public void addRekamMedis(RekamMedis rm) {
        this.daftarRekamMedis.add(rm);
    }

    public List<RekamMedis> getDaftarRekamMedis() {
        return daftarRekamMedis;
    }

    public void setDaftarRekamMedis(List<RekamMedis> daftarRekamMedis) {
        this.daftarRekamMedis = daftarRekamMedis;
    }
}