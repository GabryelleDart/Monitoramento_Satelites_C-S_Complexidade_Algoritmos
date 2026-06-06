package br.edu.ifba.satelites;

import br.edu.ifba.satelites.impl.Leitura;
import br.edu.ifba.satelites.impl.OperacoesImpl;
import br.edu.ifba.satelites.impl.Satelite;
import br.edu.ifba.satelites.operacoes.Operacoes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("satelites")
public class Rotas {
    
    private static final int LIMIAR_TEMPERATURA = 15;
    private static final int LIMIAR_FOCO_ATIVO = 0;

    private static Operacoes<Satelite, Leitura> operacoes = null;
    private static Operacoes<Satelite, Leitura> getOperacoes() {
        if (operacoes == null) {
            operacoes = new OperacoesImpl();
        }
        return operacoes;
    }

    private static final String INFORMACOES = "servico de monitoramento de satelites e focos de incendio, v1.0";

    @GET
    @Path("/")
    public Response getInformacoes() {
        return Response.ok(INFORMACOES, MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("/{id}/{temperatura}/{deteccaoFumaca}") // Adicionado a barra inicial implícita e padronizado o nome
    public Response gravarLeitura(
            @PathParam("id") String idSatelite, 
            @PathParam("temperatura") int temperatura, 
            @PathParam("deteccaoFumaca") boolean deteccaoFumaca) { // Alinhado com o nome da URL
        
        Satelite satelite = new Satelite(idSatelite, "Militar-Geostacionario");
        Leitura leitura = new Leitura(temperatura, deteccaoFumaca); // Usando a variável padronizada

        getOperacoes().gravar(satelite, leitura);

        return Response.ok().build();
    }

    @GET
    @Path("detectar")
    public Response ejecutarDeteccao() {
        long tempoInicial = System.currentTimeMillis();
        
        int totalFocos = getOperacoes().detectarIncendio(LIMIAR_TEMPERATURA, LIMIAR_FOCO_ATIVO);
        
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;

        String resultado = "--- ANALISE CONCLUIDA (VERSAO 1) ---\n" +
                            "Focos Identificados: " + totalFocos + "\n" +
                            "Tempo de Execucao: " + tempoTotal + " ms\n" +
                            "Complexidade Analitica: O(N * M^2)\n";

        return Response.ok(resultado, MediaType.TEXT_PLAIN).build();
    }
}