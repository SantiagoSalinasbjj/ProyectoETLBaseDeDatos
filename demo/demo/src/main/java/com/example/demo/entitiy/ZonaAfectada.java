package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "zona_afectada")
@Entity
@Data
public class ZonaAfectada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_mapa;
    private double latitud;
    private double longitud;
    @ManyToOne
    @JoinColumn(name = "codigo")
    private Barrio barrio;



}
