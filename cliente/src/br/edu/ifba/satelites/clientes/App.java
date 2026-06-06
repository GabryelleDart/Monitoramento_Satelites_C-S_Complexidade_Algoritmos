package br.edu.ifba.satelites.clientes;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifba.satelites.clientes.impl.ClienteImpl;
import br.edu.ifba.satelites.clientes.impl.Satelite;
import br.edu.ifba.satelites.clientes.impl.SensoriamentoImpl;

public class App {

    private static final int TOTAL_SATELITES = 10;

    public static void main(String[] args) throws Exception {
        List<Thread> processos = new ArrayList<>();

        for (int i = 0; i < TOTAL_SATELITES; i++) {
            String id = "SAT-ALFA-" + (i + 1);

            ClienteImpl cliente = new ClienteImpl();
            cliente.configurar(new Satelite(id, "Orbital-Baixo"), new SensoriamentoImpl());

            Thread processo = new Thread(cliente);
            processos.add(processo);
            processo.start(); 
        }
    
        for (Thread processo: processos) {
            processo.join();
        }

        System.out.println(">>> Simulacao Concluida: Todas as telemetrias foram enviadas ao servidor Grizzly.");
    }
}