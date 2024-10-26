package com.example.demo.service;

import com.example.demo.CSV_BE.Delito;

import com.example.demo.repository.DelitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JobInsertData {

   @Autowired
   private DelitoRepository delitoRepository;
    public void insertData(List<Delito> delitos){
        AtomicReference<Delito> delitoo = new AtomicReference<>();
        try {
            delitos.forEach(
                    delito -> {
                        delitoo.set(delito);
                        delitoRepository.insertarDatosIncidente(
                                delito.isUsoArma(),
                                delito.isUsoMoto(),
                                delito.getCantidad(),
                                delito.getFecha(),
                                delito.getFranja(),
                                delito.getDia(),
                                delito.getAño(),
                                delito.getMes(),
                                delito.getBarrio(),
                                delito.getBarrio(),
                                delito.getComuna());

                    }

            );
        }
        catch(Exception ex){
               System.out.println(ex);
        }


    }
}
