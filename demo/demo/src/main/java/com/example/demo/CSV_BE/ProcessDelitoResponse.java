package com.example.demo.CSV_BE;

import lombok.Data;

import java.util.List;

@Data
public class ProcessDelitoResponse {
    private String message;
    private short statusCode;
    private List<Delito> delitosProcesados;

    public ProcessDelitoResponse(String message,short statusCode,List<Delito>delitos){
        this.message = message;
        this.statusCode = statusCode;
        this.delitosProcesados = delitos;

    }

}
