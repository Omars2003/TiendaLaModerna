package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VentaTotal;

@Repository
public class VentaTotalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VentaTotal> findAll() {
        String sql = "SELECT id_venta, fecha, cliente, total FROM VistaTotalVentas";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            VentaTotal v = new VentaTotal();
            v.setIdVenta(rs.getLong("id_venta"));
            v.setFecha(rs.getDate("fecha"));
            v.setCliente(rs.getString("cliente"));
            v.setTotal(rs.getBigDecimal("total"));
            return v;
        });
    }
}