package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCTO")
@Data
public class Producto {
    @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
@SequenceGenerator(name = "producto_seq", sequenceName = "producto_seq", allocationSize = 1)
private Long id;

    private String nombre;

    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;

    private Integer stock;

    @Column(name = "FECHA_CADUCIDAD")
    private LocalDate fechaCaducidad;

    @Column(name = "ID_PROVEEDOR")
    private Long idProveedor;
}