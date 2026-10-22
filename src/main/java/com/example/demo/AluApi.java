package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositorio.AluRepo;
import com.example.demo.Repositorio.CursoRepo;
import com.example.demo.Repositorio.EspeRepo;
import com.example.demo.modelo.Curso;
import com.example.demo.modelo.Espe;
import com.example.demo.modelo.Matricula;
import com.example.demo.servicio.ProceServicio;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AluApi {

    @Autowired
    private EspeRepo seresp;

    @Autowired
    private CursoRepo sercur;

    @Autowired
    private AluRepo seralu;

    @Autowired
    private ProceServicio serpro;

    @GetMapping("/especialidades")
    public List<Espe> listarEspecialidades() {
        return seresp.findAll();
    }

    @GetMapping("/cursos/{codesp}")
    public List<Curso> listarCursos(@PathVariable String codesp) {
        return sercur.findByCodes(codesp);
    }

    @GetMapping("/alumnos/{codcur}")
    public List<Matricula> listarAlumnos(@PathVariable String codcur) {
        return seralu.findByCodcur(codcur);
    }

    @PostMapping("/adiciona")
    public Map<String, Object> adicionar(@RequestBody Matricula alumno) {
        Map<String, Object> resp = new HashMap<>();
        try {
            serpro.ejecutarSpadicion(alumno);
            resp.put("ok", true);
            resp.put("mensaje", "Matrícula ingresada correctamente");
        } catch (Exception e) {
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMessage());
        }
        return resp;
    }

    @DeleteMapping("/borra/{id}")
    public Map<String, Object> borrar(@PathVariable String id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            serpro.ejecutaBorra(id);
            resp.put("ok", true);
            resp.put("mensaje", "Alumno eliminado correctamente");
        } catch (Exception e) {
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMessage());
        }
        return resp;
    }

    @GetMapping("/curso/{codcur}")
    public Curso getCurso(@PathVariable String codcur) {
        return sercur.findById(codcur).orElse(null);
    }
}