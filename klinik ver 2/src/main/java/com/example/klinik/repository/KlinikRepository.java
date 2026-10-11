package com.example.klinik.repository;

import com.example.klinik.model.Klinik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlinikRepository extends JpaRepository<Klinik, Long> {
}