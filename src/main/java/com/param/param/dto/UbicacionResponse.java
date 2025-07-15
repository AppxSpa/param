package com.param.param.dto;

public class UbicacionResponse {

    private Long id;
    private String nombre;
    private String direccion;

    

    public UbicacionResponse() {
    }

    
    public UbicacionResponse(String nombre, String direccion, Long id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id=id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

}
