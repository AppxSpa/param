package com.param.param.dto;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoList {

    private Long id;
    private String nombre;
    private String nivel;
    private List<DepartamentoList> dependencias = new ArrayList<>();
    private String ubicacion;
    private String direccion;
    private String nombreJefe;
    private Integer rutJefe;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<DepartamentoList> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<DepartamentoList> dependencias) {
        this.dependencias = dependencias;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public Integer getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(Integer rutJefe) {
        this.rutJefe = rutJefe;
    }

}