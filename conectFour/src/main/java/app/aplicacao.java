package app;


//import model.Denuncia;
import com.google.gson.Gson;

import static spark.Spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.JogoService;

//import service.DenunciaService;
import spark.Route;

public class aplicacao {
	
	private static JogoService jogoService = new JogoService();
	
    public static void main(String[] args) {
        port(6798);

        
        staticFiles.location("/Ligue4FrontEndo");	
        
        get("/comecar", (request, response) -> jogoService.iniciar(request, response));	
        post("/jogada", (request, response) -> jogoService.jogada(request, response));
        /*
         *
        
        get("/produto/update/:id", (request, response) -> tarefaService.update(request, response));
        get("/produto/delete/:id", (request, response) -> denunciaService.delete(request, response));
        get("/produto", (request, response) -> denunciaService.getAll(request, response));
        */             
    }
}