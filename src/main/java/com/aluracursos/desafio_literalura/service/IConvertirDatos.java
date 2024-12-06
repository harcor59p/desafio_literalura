package com.aluracursos.desafio_literalura.service;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
