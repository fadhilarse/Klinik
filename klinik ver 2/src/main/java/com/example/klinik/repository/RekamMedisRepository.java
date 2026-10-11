package com.example.klinik.repository;

import com.example.klinik.model.RekamMedis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RekamMedisRepository extends JpaRepository<RekamMedis, Long> {
    // Dipakai untuk mengambil histori medis pasien berdasarkan ID pasien (diurutkan
    // dari yang terbaru)
    List<RekamMedis> findByKunjunganPasienIdOrderByWaktuPemeriksaanDesc(Long pasienId);
}