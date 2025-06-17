package com.example.demo.repository;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.DetalleVentaId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaId> {
    List<DetalleVenta> findById_IdVenta(Long idVenta);

}