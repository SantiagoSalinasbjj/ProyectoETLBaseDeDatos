package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

    @Table(name="tipo_incidente")
    @Entity
    @Data
    public class TipoIncidente {
        @Id
        @Column(name="tipo_incidente_id")
        private int id;
        private String nombre ;
    }
