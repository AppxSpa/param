package com.param.param.services.interfaces;

import java.util.List;

import com.param.param.dto.UbicacionRequest;
import com.param.param.dto.UbicacionResponse;

public interface UbicacionService {

    void createUbicacion(UbicacionRequest request);

    List<UbicacionResponse> getUbicaciones();

}
