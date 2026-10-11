package com.example.klinik.repository;

import com.example.klinik.model.Dokter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DokterRepository extends JpaRepository<Dokter, Long> {

    // Method pencarian nama dokter (tidak peka huruf besar/kecil)
    List<Dokter> findByNamaContainingIgnoreCase(String nama);

}