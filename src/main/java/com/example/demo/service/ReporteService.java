package com.example.demo.service;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    private final JdbcTemplate jdbcTemplate;

    public ReporteService(@Autowired DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> obtenerCorteDelDia(LocalDate fecha) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("reporte_corte_dia")
            .declareParameters(
                new SqlParameter("p_fecha", Types.DATE),
                new SqlOutParameter("cur", OracleTypes.CURSOR, new ColumnMapRowMapper())
            );

        Map<String, Object> result = jdbcCall.execute(Date.valueOf(fecha));
        return (List<Map<String, Object>>) result.get("cur");
    }

    public List<Map<String, Object>> obtenerCorteIntervalo(LocalDate inicio, LocalDate fin) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("reporte_corte_intervalo")
            .declareParameters(
                new SqlParameter("p_inicio", Types.DATE),
                new SqlParameter("p_fin", Types.DATE),
                new SqlOutParameter("cur", OracleTypes.CURSOR, new ColumnMapRowMapper())
            );

        Map<String, Object> result = jdbcCall.execute(Date.valueOf(inicio), Date.valueOf(fin));
        return (List<Map<String, Object>>) result.get("cur");
    }

    public List<Map<String, Object>> obtenerVentasSemanales() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("reporte_ventas_semanal")
            .declareParameters(
                new SqlOutParameter("cur", OracleTypes.CURSOR, new ColumnMapRowMapper())
            );

        Map<String, Object> result = jdbcCall.execute();
        return (List<Map<String, Object>>) result.get("cur");
    }
}
