package br.edu.ifba.satelites.clientes.impl;

public class Satelite implements Comparable<Satelite> {
    private String identificacao = "";
    private String modelo = "";
    // O(1)
    public Satelite(String identificacao, String modelo) {
        this.identificacao = identificacao;
        this.modelo = modelo;
    }
     // O(1)
    public String getIdentificacao() { return identificacao; }
     // O(1)
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
     // O(1)
    public String getModelo() { return modelo; }
     // O(1)
    public void setModelo(String modelo) { this.modelo = modelo; }
     // O(1)
    @Override
    public String toString() { return "satelite: " + identificacao; }
     // O(1)
    @Override
    public int compareTo(Satelite outroSatelite) {
        return identificacao.compareTo(outroSatelite.getIdentificacao());
    }
}