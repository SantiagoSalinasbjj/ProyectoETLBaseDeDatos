package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "barrio")
@Entity
@Data

public class Barrio {

    @Id
    @Column(name = "barrio_id")
    private String id;
    private String barrio;
    private byte comuna;
    @OneToMany(mappedBy = "barrio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DelitoEntity> incidentes;

}
