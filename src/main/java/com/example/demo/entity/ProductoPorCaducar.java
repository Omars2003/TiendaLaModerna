package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "VISTA_POR_CADUCAR")
@Data
public class ProductoPorCaducar {

    @Id
    private Long id;

    private String nombre;

    @Column(name = "FECHA_CADUCIDAD")
    private LocalDate fechaCaducidad;
}
