package com.aluracursos.desafio_literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try{
            return objectMapper.readValue(json, clase);
        }catch (JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
    }
}
