package com.drogueria.medicamentos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "medicamentos")
@Data
public class Medicamento  {
    @Id
    private String id;

    private String nombre;
    private String lab_fabrica;
    private Date fecha_fabricacion;
    private Date fecha_vencimiento;
    private int stock;
    private double valor_unitario;

}
