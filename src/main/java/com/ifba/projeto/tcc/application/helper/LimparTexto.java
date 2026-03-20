package com.ifba.projeto.tcc.application.helper;

public class LimparTexto {
    public static  String executar(String texto) {
        texto = texto.replaceAll("[^\\x20-\\x7E\\n]", " ");
        texto = texto.replaceAll("\\s+", " ").trim();
        return texto;
    }
}
