package com.example.demo.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Incidente")
@Data
@Getter
@Setter
@NamedStoredProcedureQuery(
        name = "sp_InsertarDatosIncidente",
        procedureName = "sp_InsertarDatosIncidente",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "uso_arma", type = Boolean.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "uso_moto", type = Boolean.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cantidad", type = Short.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha", type = LocalDate.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "franja", type = Byte.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre_dia", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "anio", type = Short.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre_mes", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigo_barrio", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre_barrio", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "comuna", type = Byte.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "tipo", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "subtipo", type = String.class)
        }
)
public class DelitoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidente")
    private int id;

    @Column(name = "anio")
    private short año;

    @Column(name = "nombre_mes")
    private String mes;

    @Column(name = "nombre_dia")
    private String dia;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "franja")
    private byte franja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barrio_id", nullable = false)
    private Barrio barrio;

    @ManyToOne
    @JoinColumn(name = "subtipo_incidente_id")
    private SubtipoIncidente subtipoIncidente;


    @Column(name = "uso_arma")
    private boolean usoArma;

    @Column(name = "uso_moto")
    private boolean usoMoto;

    @Column(name = "cantidad")
    private short cantidad;

    @Override
    public String toString() {
        return "Delito{" +
                "idMapa=" + id +
                ", año=" + año +
                ", mes='" + mes + '\'' +
                ", dia='" + dia + '\'' +
                ", fecha=" + fecha +
                ", franja=" + franja +
                ", usoArma=" + usoArma +
                ", usoMoto=" + usoMoto +
                ", cantidad=" + cantidad +
                '}';
    }
}

