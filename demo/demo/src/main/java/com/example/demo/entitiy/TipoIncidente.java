package com.example.demo.entitiy;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="tipo_incidente")
@Entity
@Data
public class TipoIncidente {
    @Id
    private int id;
    private String nombre ;
}
