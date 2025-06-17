package com.example.demo.dto;
import java.util.List;

import lombok.Data;
@Data
public class VentaConProductosDTO {
    private Long idCliente;
    private List<DetalleVentaDTO> productos;
}