package com.example.klinik.repository;

import com.example.klinik.model.Ruangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuanganRepository extends JpaRepository<Ruangan, Long> {
}