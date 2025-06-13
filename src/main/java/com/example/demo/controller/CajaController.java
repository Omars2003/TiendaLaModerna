package com.example.demo.controller;

import com.example.demo.entity.Caja;
import com.example.demo.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/caja")
public class CajaController {

    @Autowired
    private CajaService service;

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
}
