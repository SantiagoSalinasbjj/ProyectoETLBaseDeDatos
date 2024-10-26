package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "subtipo_incidente")
@Entity
@Data
public class SubtipoIncidente {
    @Id
    @Column(name="subtipo_incidente_id")
    private int id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoIncidente tipoIncidente;


}
