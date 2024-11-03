package com.example.demo.service;

import com.example.demo.CSV_BE.Delito;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ExtractDataFromCSVService {

    public List<Delito> extractInfoFromCsv(String path) throws FileNotFoundException {
        Reader reader = new InputStreamReader(new FileInputStream(path));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<Delito> delitos = new ArrayList<Delito>() ;
        csvReader.forEach(str -> {

            Delito delito = new Delito();

            delito.setMes(str[2]);
            delito.setDia(str[3]);
            delito.setFecha(LocalDate.parse(str[4]));



            delito.setAño(
                    (NumberUtils.isCreatable(str[1]))?Short.parseShort(str[1]):-1);
            delito.setFranja(
                    (NumberUtils.isCreatable(str[5]))?Byte.parseByte(str[5]):-1);
            delito.setComuna(
                    (NumberUtils.isCreatable(str[11]))?Byte.parseByte(str[11]):-1);

            delito.setCantidad(
                    (NumberUtils.isCreatable(str[14]))?Short.parseShort(str[14]):-1);


            delito.setTipo(
                    (str[6]!=null&&!str[6].equals("NULL")?str[6]:"VACIO"));
            delito.setSubtipo(
                    (str[7]!=null&&!str[7].equals("NULL"))?str[7]:"VACIO");
            delito.setUsoArma(("SI".equalsIgnoreCase(str[8])));
            delito.setUsoMoto(("SI".equalsIgnoreCase(str[9])));
            delito.setBarrio((str[10]!=null&&!str[10].equals("NULL"))?str[10]:"VACIO");
            System.out.println(delito.toString());

       /* if(Integer.parseInt(str[0].toString())==534813) {
            try {
                FileOutputStream input = new FileOutputStream("test.txt");
                String text = delito.toString();
                input.write( text.getBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        };*/
            delitos.add(delito); });
        /*AtomicReference<Delito> del = new AtomicReference<>();
        delitos.forEach(d->{if(d.getTipo().equals("VACIO")) try {
            del.set(d);
            throw new Exception("");
        } catch (Exception e) {
            System.out.println(del.toString());
        }
        });*/
        return delitos;
    }
}
