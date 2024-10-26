package com.example.demo.service;

import com.example.demo.CSV_BE.Delito;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


            delito.setTipo(str[6]);
            delito.setSubtipo(str[7]);
            delito.setUsoArma(Boolean.parseBoolean(str[8]));
            delito.setUsoMoto(Boolean.parseBoolean(str[9]));
            delito.setBarrio(str[10]);
            System.out.println(delito.toString());

       /* if(Integer.parseInt(str[0].trim())==705609) {
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

        return delitos;
    }
}
