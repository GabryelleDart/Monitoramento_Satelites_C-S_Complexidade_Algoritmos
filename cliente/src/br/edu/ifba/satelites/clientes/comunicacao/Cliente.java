package br.edu.ifba.satelites.clientes.comunicacao;

import br.edu.ifba.satelites.clientes.sensoriamento.Sensoriamento;

public interface Cliente<Monitorado, Leitura> {
    public void configurar(Monitorado monitorado, Sensoriamento<Leitura> sensoriamento);
    public Resultado enviar(Leitura leitura) throws Exception;
}