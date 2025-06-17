package com.example.demo.controller;

import com.example.demo.dto.DetalleVentaDTO;
import com.example.demo.dto.ProductoTicketDTO;
import com.example.demo.dto.VentaConProductosDTO;
import com.example.demo.dto.VentaTicketDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.DetalleVentaId;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;
import com.example.demo.entity.VentaTotal;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DetalleVentaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaTotalRepository;
import com.example.demo.service.VentaService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaTotalRepository ventaTotalRepository;

    @Autowired
private ClienteRepository clienteRepository;



    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAll() {
        return ventaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        return ventaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta create(@RequestBody Venta venta) {
        return ventaService.save(venta);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Venta> update(@PathVariable Long id, @RequestBody Venta venta) {
        return ventaService.findById(id)
                .map(v -> {
                    venta.setId(id);
                    return ResponseEntity.ok(ventaService.save(venta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ventaService.findById(id).isPresent()) {
            ventaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

     @GetMapping("/totales")
    public List<VentaTotal> getVentasConTotales() {
        return ventaTotalRepository.findAll();
    }



@PostMapping("/completa")
@Transactional
public ResponseEntity<Map<String, Long>> crearVentaCompleta(@RequestBody VentaConProductosDTO ventaDTO) {
    // Crear nueva venta
    Venta venta = new Venta();
    venta.setFecha(LocalDate.now());
    venta.setIdCliente(ventaDTO.getIdCliente());
    venta.setTotal(0.0); // Se calculará al final

    // Guardar venta primero para obtener el ID generado
    Venta savedVenta = ventaService.save(venta); // ← AQUÍ SE DEFINE

    double total = 0.0;

    for (DetalleVentaDTO dto : ventaDTO.getProductos()) {
        Producto producto = productoRepository.findById(dto.getIdProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        double subtotal = producto.getPrecioUnitario() * dto.getCantidad();

        DetalleVenta detalle = new DetalleVenta();
        DetalleVentaId id = new DetalleVentaId();
        id.setIdVenta(savedVenta.getId()); // ← Aquí sí puedes usar savedVenta
        id.setIdProducto(dto.getIdProducto());

        detalle.setId(id);
        detalle.setVenta(savedVenta);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(subtotal);

        detalleVentaRepository.save(detalle);
        total += subtotal;
    }

    // Actualizar total de la venta
    savedVenta.setTotal(total);
    ventaService.save(savedVenta);

    return ResponseEntity.ok(Map.of("idVenta", savedVenta.getId()));
}


@GetMapping("/{id}/ticket")
public ResponseEntity<VentaTicketDTO> getTicket(@PathVariable Long id) {
    Venta venta = ventaService.findById(id).orElseThrow();

    Cliente cliente = clienteRepository.findById(venta.getIdCliente()).orElseThrow();

    List<DetalleVenta> detalles = detalleVentaRepository.findById_IdVenta(id);

    List<ProductoTicketDTO> productos = detalles.stream().map(det -> {
        ProductoTicketDTO p = new ProductoTicketDTO();
        p.setNombreProducto(det.getProducto().getNombre());
        p.setCantidad(det.getCantidad());
        p.setPrecioUnitario(det.getProducto().getPrecioUnitario());
        p.setSubtotal(det.getSubtotal());
        return p;
    }).toList();

    VentaTicketDTO ticket = new VentaTicketDTO();
    ticket.setIdVenta(venta.getId());
    ticket.setCliente(cliente.getNombre());
    ticket.setFecha(venta.getFecha());
    ticket.setProductos(productos);
    ticket.setTotal(venta.getTotal());

    return ResponseEntity.ok(ticket);
}

}
