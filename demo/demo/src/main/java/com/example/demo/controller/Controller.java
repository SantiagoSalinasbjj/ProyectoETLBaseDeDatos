package com.example.demo.controller;

import com.example.demo.CSV_BE.Delito;
import com.example.demo.CSV_BE.ProcessDelitoResponse;
import com.example.demo.service.ExtractDataFromCSVService;
import com.example.demo.service.JobInsertData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ExtractDataFromCSVService extractService;
    @Autowired
    private JobInsertData jobInsertDataService;
@PostMapping("/startBatch")

    public ResponseEntity<ProcessDelitoResponse> startBatch(@RequestBody String body) throws IOException {
    ProcessDelitoResponse response=null;

        try{
            List<Delito> delitos = extractService.extractInfoFromCsv(body);
            jobInsertDataService.insertData(delitos);
            response = new ProcessDelitoResponse("Se ha procesado correctamente el archivo CSV:"+body,(short)200,delitos);
        return ResponseEntity.ok(response);}
        catch(Exception ex){
            String message= "Error al procesar el archivo CSV";
            response = new ProcessDelitoResponse("Error al procesar el archivo CSV: "+body,(short)500,null);
            System.out.print(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        }

}
