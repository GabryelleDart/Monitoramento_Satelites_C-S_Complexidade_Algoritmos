package br.edu.ifba.satelites.servidor.operacoes;

public interface Operacoes<Monitorado, Leitura> {
    // O(1) na inserção
    public void gravar(Monitorado monitorado, Leitura leitura);
    // O(M * N^2)- Função d.4 adaptada para a arquitetura do servidor
    public int detectarIncendio(int limiarTemperatura, int limiarFocoAtivo);
}