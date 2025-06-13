package com.example.demo.repository;

import com.example.demo.entity.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CajaRepository extends JpaRepository<Caja, LocalDate> {
    Optional<Caja> findByFecha(LocalDate fecha);
}
