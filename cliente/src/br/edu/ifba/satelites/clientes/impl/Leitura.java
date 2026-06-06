package br.edu.ifba.satelites.clientes.impl;

public class Leitura {
    private Integer temperatura = 0;
    private Boolean deteccaoFumaca = false;

    // Complexidade: O(1) - Construtor padrão para inicializar as variáveis na memória.
    public Leitura(Integer temperatura, Boolean deteccaoFumaca) {
        this.temperatura = temperatura;
        this.deteccaoFumaca = deteccaoFumaca;
    }
    
    public Integer getTemperatura() { return temperatura; }
    public void setTemperatura(Integer temperatura) { this.temperatura = temperatura; }
    public Boolean getDeteccaoFumaca() { return deteccaoFumaca; }
    public void setDeteccaoFumaca(Boolean deteccaoFumaca) { this.deteccaoFumaca = deteccaoFumaca; }

    @Override
    public String toString() {
        return "temperatura: " + temperatura + ", fumaça detectada: " + deteccaoFumaca;
    }
}