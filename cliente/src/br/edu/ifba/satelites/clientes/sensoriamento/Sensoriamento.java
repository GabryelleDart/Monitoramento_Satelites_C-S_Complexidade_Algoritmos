package br.edu.ifba.satelites.clientes.sensoriamento;

import java.util.List;

public interface Sensoriamento<Leitura> {
    public List<Leitura> gerar(int totalLeituras);
}