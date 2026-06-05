package br.edu.ifba.satelites.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import br.edu.ifba.satelites.operacoes.Operacoes;

public class OperacoesImpl implements Operacoes<Satelite, Leitura> {

    private static final int LIMIAR_ROTACIONAMENTO_LEITURAS = 5000;
    private Map<Satelite, Queue<Leitura>> bancoDeDados = new TreeMap<>();

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
        }
        leituras.add(leitura);

        System.out.println("gravada nova leitura para o satelite: " + satelite);
    }

    @Override
    public int detectarIncendio(int limiarTemperatura, int limiarFocoAtivo) {
        int contador = 0;

        for (Satelite satelite : bancoDeDados.keySet()) {
            List<Leitura> leiturasPorSatelite = new ArrayList<>(bancoDeDados.get(satelite));
            int n = leiturasPorSatelite.size();

            try {
                Thread.sleep(15); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int altaTemperatura = Math.abs(leiturasPorSatelite.get(i).getTemperatura_de_brilho() - 
                                                   leiturasPorSatelite.get(j).getTemperatura_de_brilho());
                    
                    int focoAtivo = Math.abs(Boolean.compare(leiturasPorSatelite.get(i).getDeteccao_fumaca(), 
                                                             leiturasPorSatelite.get(j).getDeteccao_fumaca()));

                    if (altaTemperatura > limiarTemperatura || focoAtivo > limiarFocoAtivo) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
}