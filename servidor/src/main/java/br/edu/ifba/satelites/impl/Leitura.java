package br.edu.ifba.satelites.impl;

public class Leitura {

    private Integer temperatura_de_brilho = 0;
    private boolean deteccao_fumaca = false;

    public Leitura(Integer temperatura_de_brilho, boolean deteccao_fumaca) {
        this.temperatura_de_brilho = temperatura_de_brilho;
        this.deteccao_fumaca = deteccao_fumaca;
    }
    
    public Integer getTemperatura_de_brilho() {
        return temperatura_de_brilho;
    }

    public void setTemperatura_de_brilho(Integer temperatura_de_brilho) {
        this.temperatura_de_brilho = temperatura_de_brilho;
    }

    public boolean getDeteccao_fumaca() {
        return deteccao_fumaca;
    }

    public void setDeteccao_fumaca(boolean deteccao_fumaca) {
        this.deteccao_fumaca = deteccao_fumaca;
    }

    @Override
    public String toString() {
        return "temperatura_de_brilho: " + temperatura_de_brilho + ", deteccao_fumaca: " + deteccao_fumaca;
    }
}