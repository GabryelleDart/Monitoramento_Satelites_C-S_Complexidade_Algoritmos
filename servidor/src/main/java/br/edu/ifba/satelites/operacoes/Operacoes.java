package br.edu.ifba.satelites.operacoes;

public interface Operacoes<Monitorado, Leitura> {

    public void gravar(Monitorado monitorado, Leitura leitura);

    public int detectarIncendio(int limiarTemperatura, int limiarFocoAtivo);
}