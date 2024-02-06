package com.pereyra.appFacturacion.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class FechaServiceImp implements FechaService {


    @Value("${url.webservice.externo}") //Ruta propiety donde se guarda url del service externo
    private String urlWebserviseExterno;

    @Override
    public String obtenerFechaServidorExterno() {
            RestTemplate restTemplate= new RestTemplate();
            return restTemplate.getForObject(urlWebserviseExterno,String.class);


    }

    @Override
    public String obtenerFecha() {
        try {
            String respuestaJson = obtenerFechaServidorExterno();

            // Implementacion Jackson para analizar la respuesta JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(respuestaJson);

            // Extrae solamente el datetime
            String fecha = jsonNode.get("datetime").asText().split("T")[0];
            return fecha;
        } catch (Exception e) {
            // Si hay algún problema con el servidor externo, obtener la fecha actual de forma local
            String fechaLocal = LocalDate.now().toString();
            return fechaLocal;
        }
    }


}
