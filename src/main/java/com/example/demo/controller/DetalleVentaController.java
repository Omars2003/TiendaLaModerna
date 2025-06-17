package com.example.demo.controller;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    // Obtener todos los detalles de una venta por ID
    @GetMapping("/venta/{idVenta}")
    public List<DetalleVenta> getDetallesByVenta(@PathVariable Long idVenta) {
        return detalleVentaRepository.findById_IdVenta(idVenta);
    }
}
