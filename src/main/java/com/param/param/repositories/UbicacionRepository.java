package com.param.param.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.param.param.entities.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {

    Optional<Ubicacion> findByNombre(String nombre);

}
