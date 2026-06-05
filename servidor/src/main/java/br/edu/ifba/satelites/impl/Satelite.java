package br.edu.ifba.satelites.impl;

public class Satelite implements Comparable<Satelite> {

    private String identificacao = "";
    private String modelo = "";

    public Satelite(String identificacao, String modelo) {
        this.identificacao = identificacao;
        this.modelo = modelo;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "satelite: " + identificacao;
    }

    @Override
    public int compareTo(Satelite outroSatelite) {
        return this.identificacao.compareTo(outroSatelite.getIdentificacao());
    }
}