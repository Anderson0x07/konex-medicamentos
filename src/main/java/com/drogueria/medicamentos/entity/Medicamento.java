package com.drogueria.medicamentos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

/*@Entity
@Table(name = "medicamento")*/
@Document(collection = "medicamentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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
