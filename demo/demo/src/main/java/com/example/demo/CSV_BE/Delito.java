package com.example.demo.CSV_BE;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class Delito {
    private int idMapa;
    private short año;
    private String mes;
    private String dia;
    private LocalDate fecha;
    private byte franja;
    private String tipo;
    private String subtipo;
    private boolean usoArma;
    private boolean usoMoto;
    private String barrio;
    private byte comuna;
    private double latitud;
    private double longitud;
    private short cantidad;

    @Override
    public String toString() {
        return "Delito{" +
                "idMapa=" + idMapa +
                ", año=" + año +
                ", mes=" + mes +
                ", dia=" + dia +
                ", fecha=" + fecha +
                ", franja=" + franja +
                ", tipo='" + tipo + '\'' +
                ", subtipo='" + subtipo + '\'' +
                ", usoArma=" + usoArma +
                ", usoMoto=" + usoMoto +
                ", barrio='" + barrio + '\'' +
                ", comuna=" + comuna +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", cantidad=" + cantidad +
                '}';
    }
}
