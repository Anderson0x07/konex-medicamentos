package com.drogueria.medicamentos.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/*@Entity
@Table(name = "medicamento")*/
@Document(collection = "medicamentos")
@Data
public class Medicamento {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nombre;
    private String lab_fabrica;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fabricacion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_vencimiento;
    private int stock;
    private double valor_unitario;

}
