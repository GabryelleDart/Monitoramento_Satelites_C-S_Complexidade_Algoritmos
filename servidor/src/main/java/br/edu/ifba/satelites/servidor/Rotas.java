package br.edu.ifba.satelites.servidor;

import br.edu.ifba.satelites.servidor.impl.Leitura;
import br.edu.ifba.satelites.servidor.impl.OperacoesImpl;
import br.edu.ifba.satelites.servidor.impl.Satelite;
import br.edu.ifba.satelites.servidor.operacoes.Operacoes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("satelites")
public class Rotas {
    
    private static final int LIMIAR_TEMPERATURA = 5;
    private static final int LIMIAR_FOCO_ATIVO = 0;

    private static Operacoes<Satelite, Leitura> operacoes = null;
    // Complexidade: O(1)
    private static Operacoes<Satelite, Leitura> getOperacoes() {
        if (operacoes == null) {
            operacoes = new OperacoesImpl();
        }
        return operacoes;
    }

    private static final String INFORMACOES = "Serviço de monitoramento de satelites(Focos de incêndio), v1.0";
    
    // Complexidade: O(1)
    @GET
    @Path("/")
    public Response getInformacoes() {
        return Response.ok(INFORMACOES, MediaType.TEXT_PLAIN).build();
    }
    // Complexidade: O(1)
    @POST
    @Path("/{id}/{temperatura_de_brilho}/{deteccao_fumaca}") // Adicionado a barra inicial implícita e padronizado o nome
    public Response gravarLeitura(
            @PathParam("id") String idSatelite, 
            @PathParam("temperatura_de_brilho") int temperaturaDeBrilho, // Alinhado com o nome da URL
            @PathParam("deteccao_fumaca") boolean deteccaoFumaca) { // Alinhado com o nome da URL
        
        Satelite satelite = new Satelite(idSatelite, "único");
        Leitura leitura = new Leitura(temperaturaDeBrilho, deteccaoFumaca); // Usando a variável padronizada

        getOperacoes().gravar(satelite, leitura);

        return Response.ok().build();
    }

    // Complexidade: O(M * N^2)
    @GET
    @Path("incendios")
    public Response detectarIncendios() {
        int incendiosDetectados = getOperacoes().detectarIncendio(LIMIAR_TEMPERATURA, LIMIAR_FOCO_ATIVO);
        
        String resultado = "Total de possíveis focos/combinações de incêndio detectados: " + incendiosDetectados;
        return Response.ok(resultado, MediaType.TEXT_PLAIN).build();
    }
}