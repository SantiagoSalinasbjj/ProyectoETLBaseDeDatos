package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "barrio")
@Entity
@Data

public class Barrio {

    @Id
    private String codigo;
    private String barrio;
    private byte comuna;
    @OneToMany(mappedBy = "barrio")
    private List<ZonaAfectada> zonasAfectadas;
}
