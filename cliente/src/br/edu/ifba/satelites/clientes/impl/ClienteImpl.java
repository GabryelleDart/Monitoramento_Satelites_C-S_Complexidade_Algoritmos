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

    private static final int LIMIAR_ENVIO_TEMPERATURA = 3;
    private Leitura ultimaLeitura = new Leitura(0, false);

    @Override
    public void configurar(Satelite satelite, Sensoriamento<Leitura> sensoriamento) {
        this.satelite = satelite;
        this.sensoriamento = sensoriamento;
    }

    /**
     * Complexidade Analítica: O(1)
     * Justificativa: Não possui laços de repetição. Efetua uma chamada de rede direta e síncrona.
     */
    @SuppressWarnings("deprecation")
    @Override
    public Resultado enviar(Leitura leitura) throws Exception {
        Resultado resultado = Resultado.SUCESSO;

        // Monta a URL injetando seus atributos: /satelites/{id}/{temperatura}/{deteccaoFumaca}
        URL urlEnvio = new URL(URL_SATELITES + satelite.getIdentificacao() + "/" + leitura.getTemperatura() + "/" + leitura.getDeteccaoFumaca());

        HttpURLConnection conexao = (HttpURLConnection) urlEnvio.openConnection();
        conexao.setRequestMethod("POST");

        if (conexao.getResponseCode() != 200) {
            resultado = Resultado.ERRO;
            throw new Exception("Erro de comunicação com o servidor Grizzly.");
        }
        conexao.disconnect();

        return resultado;
    }

    /**
     * Complexidade Analítica: O(K), onde K é o TOTAL_DE_LEITURAS fixo (1000).
     * Justificativa: Um laço único percorre sequencialmente o lote de medições simuladas geradas.
     */
    @Override
    public void run() {
        List<Leitura> leituras = sensoriamento.gerar(TOTAL_DE_LEITURAS);

        for (Leitura leitura: leituras) {
            // Compara a diferença absoluta usando o seu atributo getTemperatura()
            int diferencaTemperatura = Math.abs(leitura.getTemperatura() - ultimaLeitura.getTemperatura());
            
            // Otimização de banda: Envia se a temperatura variar mais do que o limiar OU se o estado da fumaça mudar
            if (diferencaTemperatura > LIMIAR_ENVIO_TEMPERATURA || leitura.getDeteccaoFumaca() != ultimaLeitura.getDeteccaoFumaca()) {
                ultimaLeitura = leitura;
                System.out.println("Disparando telemetria: Mudança climática detectada pelo satélite.");

                try {
                    enviar(leitura);
                    Thread.sleep(50); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Transmissão descartada pelo cliente: Dados estáveis.");
            }
        }
    }
}