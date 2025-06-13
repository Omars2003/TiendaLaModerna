package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Caja")
public class Caja {

    @Id
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total_efectivo")
    private Double totalEfectivo;

    public Caja() {}

    public Caja(LocalDate fecha, Double totalEfectivo) {
        this.fecha = fecha;
        this.totalEfectivo = totalEfectivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(Double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }
}
