package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductoTicketDTO {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
}