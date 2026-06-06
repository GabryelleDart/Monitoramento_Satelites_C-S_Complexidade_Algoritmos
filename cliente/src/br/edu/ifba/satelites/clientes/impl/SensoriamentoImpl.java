package br.edu.ifba.satelites.clientes.impl;

import br.edu.ifba.satelites.clientes.sensoriamento.Sensoriamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensoriamentoImpl implements Sensoriamento<Leitura> {
    private static final int TEMPERATURA_DE_BRILHO = 47; // Temperatura ambiente típica das flores, em graus Celsius
    private static final int ALTA_TEMPERATURA = 48;

    private static final Boolean Deteccao_fumaca = true; // indica se há ou não detecção de fumaça
    private static final int FOCO_ATIVO = 48; // percentual de leituras com detecção de fumaça INDICA ALTA PROBABILIDADE DE FOGO
     
    
     // Método para gerar leituras de sensoriamento, simulando a coleta de dados de temperatura e detecção de fumaça.
     // O(N) - Gerar leituras de sensoriamento para um número total de leituras especificado
        //Foi utilizado o randomizador para gerar a temperatuara, E apartir dessa temperatura, a verificação de detecção de fumaça(Se temperatura > 47, então há detecção de fumaça) e a comparação entre as leituras para identificar possíveis incêndios.
     //foi necessário o uso da complexidade O(N) devido à necessidade de iterar sobre um loop para gerar um número total de leituras especificado. Cada iteração do loop gera uma leitura de sensoriamento, e o número total de leituras é diretamente proporcional ao número de iterações. Portanto, a complexidade total do método se torna O(N).
    
    @Override
    public List<Leitura> gerar(int totalLeituras) {
        List<Leitura> leituras = new ArrayList<>();

        Random randomizador = new Random();
        for (int i = 0; i < totalLeituras; i++) {
            int temperatura =30 + randomizador.nextInt(71); // Gera temperatura entre 30 a 100 graus Celsius

            // Detecta se é alta temperatura (> 47)
            boolean alta_Temperatura = temperatura > TEMPERATURA_DE_BRILHO;

            // Simula detecção de fumaça          
            boolean deteccaoFumaca = temperatura > TEMPERATURA_DE_BRILHO; // 5% de chance de detecção de fumaça

            Leitura leitura = new Leitura(temperatura, deteccaoFumaca);
            leituras.add(leitura);
        }

        return leituras;
    }
    
}