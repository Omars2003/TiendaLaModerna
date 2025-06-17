package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VISTA_STOCK_BAJO")
@Data
public class ProductoStockBajo {

    @Id
    private Long id;

    private String nombre;

    private Integer stock;
}
