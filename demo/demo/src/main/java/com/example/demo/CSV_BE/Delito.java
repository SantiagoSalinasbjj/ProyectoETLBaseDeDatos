package com.example.demo.CSV_BE;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class Delito {
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
    private short cantidad;

    @Override
    public String toString() {
        return "Delito{" +
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
                ", cantidad=" + cantidad +
                '}';
    }
}
