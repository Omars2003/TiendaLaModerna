package com.example.demo.controller;

import com.example.demo.dto.DetalleVentaDTO;
import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.DetalleVentaId;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;
import com.example.demo.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaRepository;
import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
public class DetalleVentaController {

    @Autowired
private ProductoRepository productoRepository;

@Autowired
private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaService service;

    @GetMapping
    public List<DetalleVenta> getAll() {
        return service.findAll();
    }

    @GetMapping("/{idVenta}/{idProducto}")
    public ResponseEntity<DetalleVenta> getById(@PathVariable Long idVenta, @PathVariable Long idProducto) {
        DetalleVentaId id = new DetalleVentaId();
        id.setIdVenta(idVenta);
        id.setIdProducto(idProducto);

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<DetalleVenta> create(@RequestBody DetalleVentaDTO dto) {
    DetalleVenta detalle = new DetalleVenta();

    DetalleVentaId id = new DetalleVentaId();
    id.setIdVenta(dto.getIdVenta());
    id.setIdProducto(dto.getIdProducto());
    detalle.setId(id);

    detalle.setCantidad(dto.getCantidad());
    detalle.setSubtotal(dto.getSubtotal());

    // Asociar las entidades completas
    Producto producto = productoRepository.findById(dto.getIdProducto())
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    Venta venta = ventaRepository.findById(dto.getIdVenta())
        .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

    detalle.setProducto(producto);
    detalle.setVenta(venta);

    return ResponseEntity.ok(service.save(detalle));
}


    @DeleteMapping("/{idVenta}/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Long idVenta, @PathVariable Long idProducto) {
        DetalleVentaId id = new DetalleVentaId();
        id.setIdVenta(idVenta);
        id.setIdProducto(idProducto);

        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
