package com.example.demo.controller;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/bajo-stock")
    public List<Producto> bajoStock() {
        return service.bajoStock();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto p) {
        return service.guardar(p);
    }
}
