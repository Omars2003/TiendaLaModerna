package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VentaTicketDTO {
    private Long idVenta;
    private String cliente;
    private LocalDate fecha;
    private List<ProductoTicketDTO> productos;
    private double total;
}

