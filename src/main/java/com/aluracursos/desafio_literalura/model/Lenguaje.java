package com.aluracursos.desafio_literalura.model;

public enum Lenguaje {
    Frances("fr"),
    Finlandes("fi"),
    Ingles("en"),
    Español("es"),
    Portugues("pt"),
    Ruso("ru"),
    Aleman("de"),
    Japones("ja"),
    Turco("tr"),
    Chino("zh");

    private String codigo;

    Lenguaje(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Lenguaje esCodigoValido(String codigo) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.getCodigo().equalsIgnoreCase(codigo)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + codigo);
    }

    public static Lenguaje desdeCodigo(String codigo) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.getCodigo().equalsIgnoreCase(codigo)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Código de lenguaje no válido: " + codigo);
    }
}
