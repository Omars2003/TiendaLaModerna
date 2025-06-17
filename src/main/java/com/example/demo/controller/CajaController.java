package com.example.demo.controller;

import com.example.demo.entity.Caja;
import com.example.demo.service.CajaService;
import com.example.demo.service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/caja")
public class CajaController {

    @Autowired
    private CajaService service;
    @Autowired
    private ReporteService reporteService;


    @GetMapping
    public List<Caja> getAll() {
        return service.findAll();
    }

    @GetMapping("/fecha")
    public ResponseEntity<Caja> getPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return service.findByFecha(fecha)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @GetMapping("/reporte-dia")
    public ResponseEntity<List<Map<String, Object>>> corteDia(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(reporteService.obtenerCorteDelDia(fecha));
    }

    @GetMapping("/reporte-intervalo")
    public ResponseEntity<List<Map<String, Object>>> corteIntervalo(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(reporteService.obtenerCorteIntervalo(inicio, fin));
    }

    @GetMapping("/reporte-semanal")
    public ResponseEntity<List<Map<String, Object>>> ventasSemanales() {
        return ResponseEntity.ok(reporteService.obtenerVentasSemanales());
    }

}
