package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductoRequestDTO {
    private String nombre;
    private double precioUnitario;
    private int stock;
    private LocalDate fechaCaducidad;
    private Long proveedorId; // Aquí se manda el ID del proveedor
}
