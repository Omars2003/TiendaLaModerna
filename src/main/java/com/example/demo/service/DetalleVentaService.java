package com.example.demo.service;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.DetalleVentaId;
import com.example.demo.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    public List<DetalleVenta> findAll() {
        return repository.findAll();
    }

    public Optional<DetalleVenta> findById(DetalleVentaId id) {
        return repository.findById(id);
    }

    public DetalleVenta save(DetalleVenta detalle) {
        return repository.save(detalle);
    }

    public void deleteById(DetalleVentaId id) {
        repository.deleteById(id);
    }
}

