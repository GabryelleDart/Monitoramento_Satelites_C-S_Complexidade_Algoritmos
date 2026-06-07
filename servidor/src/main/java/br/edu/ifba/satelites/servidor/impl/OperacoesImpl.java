package br.edu.ifba.satelites.servidor.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import br.edu.ifba.satelites.servidor.operacoes.Operacoes;

public class OperacoesImpl implements Operacoes<Satelite, Leitura> {

    private static final int LIMIAR_ROTACIONAMENTO_LEITURAS = 40;
    private Map<Satelite, Queue<Leitura>> bancoDeDados = new TreeMap<>();
    
    // Complexidade: O(1)
    @Override
    public void gravar(Satelite satelite, Leitura leitura) {
        Queue<Leitura> leituras = new LinkedList<>();
        if (bancoDeDados.containsKey(satelite)) {
            leituras = bancoDeDados.get(satelite);
        } else {
            bancoDeDados.put(satelite, leituras);
        }

        try {
            Thread.sleep(5); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (leituras.size() > LIMIAR_ROTACIONAMENTO_LEITURAS) {
            leituras.poll();

             System.out.println("limite de rotacionamento atingido, última leitura descartada");
        }
        leituras.add(leitura);

        System.out.println("gravada nova leitura para o satelite: " + satelite);
    }

    // Complexidade Geral: O(M * N^2) onde M é o número de satélites e N é a quantidade de leituras por satélite
    @Override
    public int detectarIncendio(int limiarTemperatura, int limiarFocoAtivo) {
        int contador = 0;

        // Laço externo roda M vezes (Varre todos os satélites registrados no banco)
        for (Satelite satelite : bancoDeDados.keySet()) {
            List<Leitura> leiturasPorSatelite = new ArrayList<>(bancoDeDados.get(satelite));
            int n = leiturasPorSatelite.size();

            // Simula o delay de processamento por lote exigido pelo professor
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Laço duplo combinatório O(N^2) para cruzar todas as leituras daquele satélite
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    
                    // Cálculo da variação de temperatura absoluta exatamente como na sua Avaliação 1
                    int Alta_temperatura = Math.abs(leiturasPorSatelite.get(i).getTemperatura_de_brilho() -
                            leiturasPorSatelite.get(j).getTemperatura_de_brilho());
                    int Foco_ativo = Math.abs(Boolean.compare(leiturasPorSatelite.get(i).getDeteccao_fumaca(), leiturasPorSatelite.get(j).getDeteccao_fumaca()));

                    if (Alta_temperatura > limiarTemperatura || Foco_ativo > limiarFocoAtivo) {
                        contador++;
                    }
                }
            }
        }

        return contador;
    }
}