package br.edu.ifba.satelites.clientes.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifba.satelites.clientes.sensoriamento.Sensoriamento;

public class SensoriamentoImpl implements Sensoriamento<Leitura> {
    private static final int TEMPERATURA_BRILHO_NORMAL = 47; // Temperatura ambiente típica das flores, em graus Celsius
    private static final int ALTA_TEMPERATURA = 50;
    
    // Complexidade Geral: O(N)
    @Override
    public List<Leitura> gerar(int totalLeituras) {
        // O(1)
        List<Leitura> leituras = new ArrayList<>();
        Random randomizador = new Random();
        // O(N)
        for (int i = 0; i < totalLeituras; i++) {
            // O(1)
            int oscilacao = TEMPERATURA_BRILHO_NORMAL * randomizador.nextInt(ALTA_TEMPERATURA)/100;
            int temperatura_de_brilho = randomizador.nextBoolean()? TEMPERATURA_BRILHO_NORMAL + oscilacao: TEMPERATURA_BRILHO_NORMAL - oscilacao;
            // O(1)
            boolean deteccao_fumaca = randomizador.nextBoolean();            
            // O(1)
            leituras.add(new Leitura(temperatura_de_brilho, deteccao_fumaca));
        }

        return leituras;
    }
    
}