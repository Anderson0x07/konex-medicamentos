package com.drogueria.medicamentos.repository;

import com.drogueria.medicamentos.entity.Medicamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository extends MongoRepository<Medicamento, String> {
    Optional<Medicamento> findByNombre(String nombre);
}
