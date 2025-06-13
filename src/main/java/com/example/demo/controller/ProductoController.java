package com.example.demo.controller;

import com.example.demo.dto.ProductoRequestDTO;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Proveedor;
import com.example.demo.repository.ProveedorRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/bajo-stock")
    public List<Producto> bajoStock() {
        return service.bajoStock();
    }

    // Nuevo método usando DTO
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody ProductoRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setStock(dto.getStock());
        producto.setFechaCaducidad(dto.getFechaCaducidad());
        producto.setProveedor(proveedor);

        Producto guardado = productoRepository.save(producto);
        return ResponseEntity.ok(guardado);
    }
}
