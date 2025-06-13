package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class DetalleVentaId implements Serializable {
    private Long idVenta;
    private Long idProducto;
}
