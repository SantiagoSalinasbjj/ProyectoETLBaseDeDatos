package com.example.demo.repository;

import com.example.demo.CSV_BE.Delito;
import com.example.demo.entitiy.DelitoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DelitoRepository extends JpaRepository<DelitoEntity,Integer> {
    @Procedure(name = "sp_InsertarDatosIncidente")
    void insertarDatosIncidente(
            @Param("uso_arma") Boolean usoArma,
            @Param("uso_moto") Boolean usoMoto,
            @Param("cantidad") Short cantidad,
            @Param("fecha") LocalDate fecha,
            @Param("franja") Byte franja,
            @Param("nombre_dia") String nombreDia,
            @Param("anio") Short anio,
            @Param("nombre_mes") String nombreMes,
            @Param("codigo_barrio") String codigoBarrio,
            @Param("nombre_barrio") String nombreBarrio,
            @Param("comuna") Byte comuna,
            @Param("tipo") String tipo,
            @Param("subtipo") String subtipo
    );

}
