package com.param.param.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.param.param.dto.CreateDireccionResponse;
import com.param.param.dto.DireccionRequest;
import com.param.param.dto.DireccionResponse;
import com.param.param.dto.UbicacionRequest;
import com.param.param.dto.UbicacionResponse;
import com.param.param.entities.Ubicacion;
import com.param.param.excpetions.UbicacionExceptions;
import com.param.param.repositories.UbicacionRepository;
import com.param.param.services.interfaces.ApiDireccionService;
import com.param.param.services.interfaces.ApiGetDireccionService;
import com.param.param.services.interfaces.UbicacionService;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    private final ApiDireccionService apiDireccionService;

    private final ApiGetDireccionService apiGetDireccionService;

    public UbicacionServiceImpl(UbicacionRepository ubicacionRepository, ApiDireccionService apiDireccionService,
            ApiGetDireccionService apiGetDireccionService) {
        this.ubicacionRepository = ubicacionRepository;
        this.apiDireccionService = apiDireccionService;
        this.apiGetDireccionService = apiGetDireccionService;
    }

    @Override
    public void createUbicacion(UbicacionRequest request) {

        Optional<Ubicacion> optUbicacion = ubicacionRepository.findByNombre(request.getNombre());

        if (optUbicacion.isPresent()) {
            throw new UbicacionExceptions("Ya existe una ubicacion con el nombre: " + request.getNombre());

        }

        CreateDireccionResponse dirId = apiDireccionService.createDireccion(new DireccionRequest(request.getCalle(),
                request.getNumero(), request.getComuna(), request.getLatitud(), request.getLongitud(),
                request.getAdicional()));

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setNombre(request.getNombre());
        ubicacion.setDirId(dirId.getDirId());

        ubicacionRepository.save(ubicacion);

    }

    @Override
    public List<UbicacionResponse> getUbicaciones() {

        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();

        return ubicaciones.stream()
                .map(ubicacion -> {

                    DireccionResponse direccionResponse = apiGetDireccionService.getDireccionById(ubicacion.getDirId());

                    return new UbicacionResponse(ubicacion.getNombre(), direccionResponse.getDireccionCompleta(),
                            ubicacion.getId());
                })
                .toList();

    }

}
