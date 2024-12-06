package com.aluracursos.desafio_literalura.service;

public interface IconvertiDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
