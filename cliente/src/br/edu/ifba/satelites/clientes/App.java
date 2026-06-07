import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.satelites.clientes.impl.ClienteImpl;
import br.edu.ifba.satelites.clientes.impl.Satelite;
import br.edu.ifba.satelites.clientes.impl.SensoriamentoImpl;

public class App {

    private static final int TOTAL_SATELITES = 10;
    // Complexidade Geral: O(M * N)
    public static void main(String[] args) throws Exception {
        // O(1)
        List<Thread> processos = new ArrayList<>();
        // O(M)
        for (int i = 0; i < TOTAL_SATELITES; i++) {
            String id = "SAT-" + (i + 1);

            ClienteImpl cliente = new ClienteImpl();
            // O(1)
            cliente.configurar(new Satelite(id, "único"), new SensoriamentoImpl());

            // O(1)
            Thread processo = new Thread(cliente);
            processos.add(processo);
            processo.start();
        }
        // O(M)
        for (Thread processo: processos) {
            processo.join();
        }
        // O(1)
        System.out.println("leituras enviadas");
    }
}