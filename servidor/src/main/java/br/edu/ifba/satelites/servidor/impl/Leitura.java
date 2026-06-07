package br.edu.ifba.satelites.servidor.impl;

public class Leitura {

    private Integer temperatura_de_brilho = 0;
    private boolean deteccao_fumaca = false;

    // Complexidade: O(1)
    public Leitura(Integer temperatura_de_brilho, boolean deteccao_fumaca) {
        this.temperatura_de_brilho = temperatura_de_brilho;
        this.deteccao_fumaca = deteccao_fumaca;
    }
    // Complexidade: O(1)
    public Integer getTemperatura_de_brilho() {
        return temperatura_de_brilho;
    }
    // Complexidade: O(1)
    public void setTemperatura_de_brilho(Integer temperatura_de_brilho) {
        this.temperatura_de_brilho = temperatura_de_brilho;
    }

    // Complexidade: O(1)
    public boolean getDeteccao_fumaca() {
        return deteccao_fumaca;
    }
    // Complexidade: O(1)
    public void setDeteccao_fumaca(boolean deteccao_fumaca) {
        this.deteccao_fumaca = deteccao_fumaca;
    }
    // Complexidade: O(1)
    @Override
    public String toString() {
        return "temperatura_de_brilho: " + temperatura_de_brilho + ", deteccao_fumaca: " + deteccao_fumaca;
    }
}