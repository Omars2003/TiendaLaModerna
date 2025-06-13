package com.example.demo.service;

import com.example.demo.entity.Caja;
import com.example.demo.repository.CajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CajaService {

    @Autowired
    private CajaRepository repository;

    public List<Caja> findAll() {
        return repository.findAll();
    }

    public Optional<Caja> findByFecha(LocalDate fecha) {
        return repository.findByFecha(fecha);
    }
}

