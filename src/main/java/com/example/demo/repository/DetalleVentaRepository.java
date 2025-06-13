package com.example.demo.repository;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.DetalleVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaId> {
}
