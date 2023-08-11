package com.drogueria.medicamentos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MedicamentoDto {

    private String id;
    private String nombre;
    private String lab_fabrica;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fabricacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_vencimiento;

    private int stock;

    private double valor_unitario;

}
