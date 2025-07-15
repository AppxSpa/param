package com.param.param.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.param.param.dto.DepartamentoList;
import com.param.param.dto.DepartamentoRequest;
import com.param.param.dto.DepartamentoResponse;
import com.param.param.dto.DireccionResponse;
import com.param.param.dto.PersonaResponse;
import com.param.param.entities.Departamento;
import com.param.param.entities.Ubicacion;
import com.param.param.excpetions.UbicacionExceptions;
import com.param.param.repositories.DepartamentoRepository;
import com.param.param.repositories.UbicacionRepository;
import com.param.param.services.interfaces.ApiGetDireccionService;
import com.param.param.services.interfaces.ApiPersonaService;
import com.param.param.services.interfaces.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    private final UbicacionRepository ubicacionRepository;

    private final ApiGetDireccionService apiGetDireccionService;

    private final ApiPersonaService apiPersonaService;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository,
            UbicacionRepository ubicacionRepository, ApiGetDireccionService apiGetDireccionService,
            ApiPersonaService apiPersonaService) {
        this.departamentoRepository = departamentoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.apiGetDireccionService = apiGetDireccionService;
        this.apiPersonaService = apiPersonaService;
    }

    @Override
    public DepartamentoResponse createDepartamento(DepartamentoRequest request) {

        Ubicacion ubicacion = ubicacionRepository.findById(request.getIdUbicacion())
                .orElseThrow(() -> new UbicacionExceptions("No existe la ubicacion"));

        Departamento departamento = new Departamento();
        departamento.setNombreDepartamento(request.getNombre());
        departamento.setNivel(Departamento.NivelDepartamento.valueOf(request.getNivel().toUpperCase()));
        departamento.setRutJefe(request.getRutJefe());
        departamento.setUbicacion(ubicacion);

        if (request.getPadreId() != null) {
            Departamento padre = departamentoRepository.findById(request.getPadreId())
                    .orElseThrow(() -> new IllegalArgumentException("Departamento padre no encontrado"));
            departamento.setDepartamentoSuperior(padre);
        }

        departamentoRepository.save(departamento);
        return new DepartamentoResponse(departamento.getId(), departamento.getNombreDepartamento());
    }

    @Override
    public DepartamentoList getDepartamentoListByDepto(Long id) {
        Departamento depto = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        return mapDto(depto);
    }

    @Override
    public List<DepartamentoList> getDepartamentosList() {
        List<Departamento> raices = departamentoRepository.findAll()
                .stream()
                .filter(d -> d.getNivel().toString().equalsIgnoreCase("ALCALDIA"))
                .toList();

        return raices.stream()
                .map(this::mapDto)
                .toList();
    }

    private DepartamentoList mapDto(Departamento departamento) {

        DepartamentoList dto = new DepartamentoList();
        dto.setId(departamento.getId());
        dto.setNombre(departamento.getNombreDepartamento());
        dto.setNivel(departamento.getNivel().toString());
        dto.setRutJefe(departamento.getRutJefe());

        DireccionResponse direccion = apiGetDireccionService.getDireccionById(departamento.getUbicacion().getDirId());
        PersonaResponse personaResponse = apiPersonaService.getPersonaInfo(departamento.getRutJefe());

        dto.setUbicacion(departamento.nombreUbicacion());
        dto.setDireccion(direccion.getDireccionCompleta());
        dto.setNombreJefe(personaResponse.getNombreCompleto());

        dto.setDependencias(
                departamento.getChildrens()
                        .stream()
                        .map(this::mapDto)
                        .toList());

        return dto;
    }

   }
