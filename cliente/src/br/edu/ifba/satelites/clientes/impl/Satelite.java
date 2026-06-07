package br.edu.ifba.satelites.clientes.impl;

public class Satelite implements Comparable<Satelite> {
    private String identificacao = "";
    private String modelo = "";
    // Complexidade: O(1)
    public Satelite(String identificacao, String modelo) {
        this.identificacao = identificacao;
        this.modelo = modelo;
    }
    // Complexidade: O(1)
    public String getIdentificacao() { return identificacao; }
    // Complexidade: O(1)
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
    // Complexidade: O(1)
    public String getModelo() { return modelo; }
    // Complexidade: O(1)
    public void setModelo(String modelo) { this.modelo = modelo; }
    // Complexidade: O(1)
    @Override
    public String toString() { return "satelite: " + identificacao; }
    // Complexidade: O(1)
    @Override
    public int compareTo(Satelite outroSatelite) {
        return identificacao.compareTo(outroSatelite.getIdentificacao());
    }
}