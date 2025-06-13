package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VENTA")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    @SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
    
    private Long id;

    private LocalDate fecha;

    private Double total;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;
}
