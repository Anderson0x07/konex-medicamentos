package com.drogueria.medicamentos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VentaDto {
    private String id;
    private LocalDateTime fecha_venta;
    private String medicamento;
    private int cantidad;
    private double valor_unitario;
    private double valor_total;

}
