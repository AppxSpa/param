package com.param.param.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.param.param.dto.UbicacionRequest;
import com.param.param.dto.UbicacionResponse;
import com.param.param.errors.ErrorResponse;
import com.param.param.excpetions.UbicacionExceptions;
import com.param.param.services.interfaces.UbicacionService;

import reactor.netty.http.server.HttpServerRequest;

@RestController
@RequestMapping("/api/param/ubicacion")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @PostMapping
    public ResponseEntity<Object> createUbicacion(@RequestBody UbicacionRequest request, HttpServerRequest serverRequest) {

        try {
            ubicacionService.createUbicacion(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (UbicacionExceptions e) {

            ErrorResponse error = new ErrorResponse();
            error.setTimestamp(LocalDateTime.now());
            error.setStatus(HttpStatus.CONFLICT.value());
            error.setError("Conflict");
            error.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

    }

    @GetMapping("/list")
    public ResponseEntity<Object> getUbicacion() {

        List<UbicacionResponse> ubicaciones = ubicacionService.getUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }

}
