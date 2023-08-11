package com.drogueria.medicamentos.service;

import com.drogueria.medicamentos.entity.Medicamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicamentoService {

    public List<Medicamento> listarMedicamentos();
    public void guardar(Medicamento medicamentoDto);

    public void eliminar(String id);

    public Medicamento editarMedicamento(String id, Medicamento medicamentoDto);

    public Medicamento encontrarMedicamento(String id);

}
