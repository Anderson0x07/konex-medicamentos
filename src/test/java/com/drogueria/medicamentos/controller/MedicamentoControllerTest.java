package com.drogueria.medicamentos.controller;

import static org.mockito.Mockito.*;
import com.drogueria.medicamentos.entity.Medicamento;
import com.drogueria.medicamentos.service.MedicamentoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class MedicamentoControllerTest {
    @InjectMocks
    private MedicamentoController medicamentoController;

    @Mock
    private MedicamentoService medicamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void testGetAllMedicamentos() {

        Medicamento med1 = new Medicamento();
        Medicamento med2 = new Medicamento();

        med1.setNombre("Test1");
        med2.setNombre("Test2");

        List<Medicamento> mockMedicamentos = new ArrayList<>();
        mockMedicamentos.add(med1);
        mockMedicamentos.add(med2);

        when(medicamentoService.listarMedicamentos()).thenReturn(mockMedicamentos);

        List<Medicamento> result = medicamentoController.getAll();

        log.info("Result: {}", result);

        verify(medicamentoService, times(1)).listarMedicamentos();

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(mockMedicamentos.get(0).getNombre(), result.get(0).getNombre());
        Assert.assertEquals(mockMedicamentos.get(1).getNombre(), result.get(1).getNombre());

    }


    @Test
    @Order(2)
    void testSaveMedicamento() {
        Medicamento mockMedicamento = new Medicamento();
        mockMedicamento.setNombre("Test");
        mockMedicamento.setLab_fabrica("Test Laboratorio");
        mockMedicamento.setFecha_fabricacion(new Date());
        mockMedicamento.setFecha_vencimiento(new Date());
        mockMedicamento.setStock(5);
        mockMedicamento.setValor_unitario(15200);

        ResponseEntity<?> responseEntity = medicamentoController.saveMedicamento(mockMedicamento);

        log.info("Result: {}",responseEntity.getBody());

        verify(medicamentoService, times(1)).guardar(mockMedicamento);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    @Order(3)
    void testGetMedicamento() {
        String mockId = "1234";
        Medicamento mockMedicamento = new Medicamento();

        when(medicamentoService.encontrarMedicamento(mockId)).thenReturn(mockMedicamento);

        Medicamento result = medicamentoController.getMedicamento(mockId);

        log.info("Result: {}",result.toString());

        verify(medicamentoService, times(1)).encontrarMedicamento(mockId);
    }

    @Test
    @Order(4)
    void testDeleteMedicamento() {
        String mockId = "1234";

        ResponseEntity<?> responseEntity = medicamentoController.deleteMedicamento(mockId);

        log.info("Result: {}",responseEntity.getBody());


        verify(medicamentoService, times(1)).eliminar(mockId);
    }

    //USANDO REST TEMPLATE
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(5)
    void testListarMedicamentos() {
        ResponseEntity<List<Medicamento>> response = testRestTemplate.exchange(
                "/api/medicamentos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
        });

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

    }
}
