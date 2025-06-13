package com.example.demo.service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> obtenerTodos() {
        return repo.findAll();
    }

    public List<Producto> bajoStock() {
        return repo.findByStockLessThan(5);
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }
}