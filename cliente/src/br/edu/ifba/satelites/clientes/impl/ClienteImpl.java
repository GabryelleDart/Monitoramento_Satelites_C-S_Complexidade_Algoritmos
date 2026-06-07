package br.edu.ifba.satelites.clientes.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.edu.ifba.satelites.clientes.comunicacao.Cliente;
import br.edu.ifba.satelites.clientes.comunicacao.Resultado;
import br.edu.ifba.satelites.clientes.sensoriamento.Sensoriamento;

public class ClienteImpl implements Cliente<Satelite, Leitura>, Runnable {

    private static final int TOTAL_DE_LEITURAS = 1000;
    private static final String URL_SERVIDOR = "http://127.0.0.1:8080"; 
    private static final String URL_SATELITES = URL_SERVIDOR + "/satelites/";

    private Satelite satelite = null;
    private Sensoriamento<Leitura> sensoriamento = null;

    private static final int LIMIAR_ENVIO_FOCO_ATIVO = 0;
    private static final int LIMIAR_ENVIO_TEMPERATURA = 5;
    private Leitura ultimaLeitura = new Leitura(0, false);

    // Complexidade: O(1)
    @Override
    public void configurar(Satelite satelite, Sensoriamento<Leitura> sensoriamento) {
        this.satelite = satelite;
        this.sensoriamento = sensoriamento;
    }

    // Complexidade: O(1)
    @SuppressWarnings("deprecation")
    @Override
    public Resultado enviar(Leitura leitura) throws Exception {
        Resultado resultado = Resultado.SUCESSO;

        // Monta a URL injetando seus atributos: /satelites/{id}/{temperatura}/{deteccaoFumaca}
        URL urlEnvio = new URL(URL_SATELITES + satelite.getIdentificacao() + "/" + leitura.getTemperatura_de_brilho() + "/" + leitura.getDeteccao_fumaca());

        HttpURLConnection conexao = (HttpURLConnection) urlEnvio.openConnection();
        conexao.setRequestMethod("POST");

        if (conexao.getResponseCode() != 200) {
            resultado = Resultado.ERRO;
            throw new Exception("Erro de comunicação com o servidor Grizzly.");
        }
        conexao.disconnect();

        return resultado;
    }

    // Complexidade Geral: O(N)
    @Override
    public void run() {
        // O(N)
        List<Leitura> leituras = sensoriamento.gerar(TOTAL_DE_LEITURAS);
        // O(N)
        for (Leitura leitura: leituras) {
            // O(1)
            int diferencaTemperatura = Math.abs(leitura.getTemperatura_de_brilho() - ultimaLeitura.getTemperatura_de_brilho());
            int diferencaFoco = Math.abs(Boolean.compare(leitura.getDeteccao_fumaca(), ultimaLeitura.getDeteccao_fumaca()));
            
            // O(1)
            if (diferencaTemperatura > LIMIAR_ENVIO_TEMPERATURA || diferencaFoco > LIMIAR_ENVIO_FOCO_ATIVO) {
                ultimaLeitura = leitura;
                System.out.println("Leitura sendo enviada ...");

                try {
                    // O(1)
                    enviar(leitura);
                    // O(1)
                    Thread.sleep(50); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // O(1)
                System.out.println("Não ocorreram diferenças significativas desde a última leitura.");
            }
        }
    }
}