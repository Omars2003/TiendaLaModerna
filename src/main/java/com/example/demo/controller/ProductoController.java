package com.example.demo.controller;

import com.example.demo.dto.ProductoRequestDTO;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductoPorCaducar;
import com.example.demo.entity.ProductoStockBajo;
import com.example.demo.entity.Proveedor;
import com.example.demo.repository.ProveedorRepository;
import com.example.demo.repository.ProductoPorCaducarRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.ProductoStockBajoRepository;
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

     @Autowired
    private ProductoStockBajoRepository stockBajoRepo;

    @Autowired
    private ProductoPorCaducarRepository porCaducarRepo;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return service.obtenerTodos();
    }

      @GetMapping("/stock-bajo")
    public List<ProductoStockBajo> obtenerStockBajo() {
        return stockBajoRepo.findAll();
    }

    @GetMapping("/por-caducar")
    public List<ProductoPorCaducar> obtenerPorCaducar() {
        return porCaducarRepo.findAll();
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
