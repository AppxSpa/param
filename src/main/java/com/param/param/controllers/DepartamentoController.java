package com.param.param.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.param.param.dto.DepartamentoList;
import com.param.param.dto.DepartamentoRequest;
import com.param.param.services.interfaces.DepartamentoService;

@RestController
@RequestMapping("/api/param/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<Object> createDepartamento(@RequestBody DepartamentoRequest departamentoRequest) {
        try {
            return ResponseEntity.ok(departamentoService.createDepartamento(departamentoRequest));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoList> obtenerJerarquia(@PathVariable Long id) {

        try {
            DepartamentoList departamentoList = departamentoService.getDepartamentoListByDepto(id);
            return ResponseEntity.ok(departamentoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<DepartamentoList>> obtenerDepartamentos() {

        try {
            List<DepartamentoList> departamentoList = departamentoService.getDepartamentosList();
            return ResponseEntity.ok(departamentoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getDepartamentoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(departamentoService.getDepartementoById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}