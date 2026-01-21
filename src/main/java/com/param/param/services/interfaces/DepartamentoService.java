package com.param.param.services.interfaces;

import java.util.List;

import com.param.param.dto.DepartamentoList;
import com.param.param.dto.DepartamentoRequest;
import com.param.param.dto.DepartamentoResponse;

public interface DepartamentoService {

    DepartamentoResponse createDepartamento(DepartamentoRequest request);

    DepartamentoList getDepartamentoListByDepto(Long id);

    List<DepartamentoList> getDepartamentosList();

    DepartamentoResponse getDepartementoById(Long id);

}
