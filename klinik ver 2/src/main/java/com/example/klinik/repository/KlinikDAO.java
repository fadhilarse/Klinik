package com.example.klinik.repository;

import com.example.klinik.model.Klinik;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // <-- Import Wajib

@Repository
public class KlinikDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional // <-- Wajib ada agar persist() diizinkan oleh JPA
    public void simpanKlinikManual(Klinik klinik) {
        entityManager.persist(klinik);
    }
}