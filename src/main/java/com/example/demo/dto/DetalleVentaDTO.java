package com.example.demo.dto;

import lombok.Data;


@Data
public class DetalleVentaDTO {
    private Long idVenta;
    private Long idProducto;
    private int cantidad;
    private double subtotal;
}
