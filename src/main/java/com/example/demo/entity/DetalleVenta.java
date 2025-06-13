package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DETALLEVENTA")
@Data
public class DetalleVenta {

    @EmbeddedId
    private DetalleVentaId id;

    @ManyToOne
@MapsId("idProducto")
@JoinColumn(name = "ID_PRODUCTO")
private Producto producto;

@ManyToOne
@MapsId("idVenta")
@JoinColumn(name = "ID_VENTA")
private Venta venta;

    private Integer cantidad;

    private Double subtotal;
}
