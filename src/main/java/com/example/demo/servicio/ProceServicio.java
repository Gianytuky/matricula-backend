package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Matricula;

@Service
public class ProceServicio {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void ejecutarSpadicion(Matricula alu) {
        String procedimiento = "CALL spadicion(?, ?, ?, ?)";

        jdbcTemplate.update(procedimiento, alu.getCodcur(), alu.getApenom(), alu.getTipo(), alu.getPago());
    }

    public void ejecutaBorra(String id) {
        String procedimiento = "CALL spanula(?)";

        jdbcTemplate.update(procedimiento, id);
    }

}
