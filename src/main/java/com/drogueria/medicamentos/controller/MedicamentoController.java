package com.drogueria.medicamentos.controller;

import com.drogueria.medicamentos.entity.Medicamento;
import com.drogueria.medicamentos.repository.MedicamentoRepository;
import com.drogueria.medicamentos.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    private Map<String,Object> response = new HashMap<>();


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medicamento> getAll() {
        return medicamentoService.listarMedicamentos();
    }

    @PostMapping
    public ResponseEntity<?> saveMedicamento(@RequestBody Medicamento medicamento) {
        medicamentoService.guardar(medicamento);
        response.clear();
        response.put("message", "Medicamento guardado exitosamente");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medicamento updateMedicamento(@PathVariable String id, @RequestBody Medicamento updatedMedicamento) {
        return medicamentoService.editarMedicamento(id, updatedMedicamento);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medicamento getMedicamento(@PathVariable String id) {
        return medicamentoService.encontrarMedicamento(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicamento(@PathVariable String id) {
        medicamentoService.eliminar(id);
        response.clear();
        response.put("message", "Medicamento eliminado exitosamente");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
