package com.example.klinik.repository;

import com.example.klinik.model.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
}