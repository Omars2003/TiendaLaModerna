package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class VentaTotal {
    private Long idVenta;
    private Date fecha;
    private String cliente;
    private BigDecimal total;
}
