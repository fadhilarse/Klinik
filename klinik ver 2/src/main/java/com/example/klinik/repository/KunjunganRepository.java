package com.example.klinik.repository;

import com.example.klinik.model.Kunjungan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KunjunganRepository extends JpaRepository<Kunjungan, Long> {

    // Dipakai untuk menyaring antrean berdasarkan status tanpa membedakan huruf
    // besar/kecil
    List<Kunjungan> findByStatusIgnoreCase(String status);

    // Method lama (tetap disimpan jika masih dibutuhkan)
    List<Kunjungan> findByStatus(String status);
}