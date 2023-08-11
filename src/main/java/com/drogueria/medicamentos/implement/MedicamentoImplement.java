package com.drogueria.medicamentos.implement;

import com.drogueria.medicamentos.entity.Medicamento;
import com.drogueria.medicamentos.exception.NotFoundException;
import com.drogueria.medicamentos.repository.MedicamentoRepository;
import com.drogueria.medicamentos.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicamentoImplement implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarMedicamentos() {

        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        if(medicamentos.isEmpty()) {
            throw new NotFoundException("No hay medicamentos registrados");
        }
        return medicamentos;
    }

    @Transactional
    @Override
    public void guardar(Medicamento medicamento) {
        if (!medicamentoRepository.findByNombre(medicamento.getNombre()).isPresent()) {
            medicamentoRepository.save(medicamento);
        } else {
            throw new NotFoundException("Medicamento ya registrado");
        }
    }

    @Override
    public void eliminar(String id) {
        medicamentoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Medicamento no encontrado")
        );
        medicamentoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Medicamento editarMedicamento(String id, Medicamento updatedMedicamento) {
        Medicamento existingMedicamento = medicamentoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Medicamento no encontrado")
        );

        if(existingMedicamento.getNombre() != null)
            existingMedicamento.setNombre(updatedMedicamento.getNombre());

        if(existingMedicamento.getLab_fabrica() != null)
            existingMedicamento.setLab_fabrica(updatedMedicamento.getLab_fabrica());

        if(existingMedicamento.getFecha_fabricacion() != null)
            existingMedicamento.setFecha_fabricacion(updatedMedicamento.getFecha_fabricacion());

        if(existingMedicamento.getFecha_vencimiento() != null)
            existingMedicamento.setFecha_vencimiento(updatedMedicamento.getFecha_vencimiento());

        if(existingMedicamento.getStock() != 0)
            existingMedicamento.setStock(updatedMedicamento.getStock());

        if(existingMedicamento.getValor_unitario() != 0)
            existingMedicamento.setValor_unitario(updatedMedicamento.getValor_unitario());

        return medicamentoRepository.save(existingMedicamento);
    }

    @Override
    public Medicamento encontrarMedicamento(String id) {
        return medicamentoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Medicamento no encontrado")
        );
    }
}
