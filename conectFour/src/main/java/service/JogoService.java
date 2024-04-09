package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import dao.*;
import model.*;
import spark.Request;
import spark.Response;
import spark.Session;

public class JogoService{
	private JogoDAO jogoDAO;
	
	public JogoService() {
		jogoDAO = new JogoDAO();
	}
	
	public Object iniciar(Request request, Response response) {
		TabuleiroService tabuleiro = new TabuleiroService();
		jogoDAO.conectar();
		int id = 1;
		int idJogador1 = 1;
		int idJogador2 = 2;
		int idTabuleiro = tabuleiro.create();
		
		
		Jogo jogo = new Jogo(id, idJogador1, idJogador2, idTabuleiro);
		
		request.session(true); 
		request.session().attribute("jogo",id);
		
		jogoDAO.add(jogo);
		jogoDAO.close();
		response.redirect("./index.html");
		return jogo;
	}

	public Object jogada(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.header("Access-Control-Allow-Headers", "Content-Type");

		int coluna = Integer.parseInt(request.queryParams("coluna"));
		
		TabuleiroService tabuleiro = new TabuleiroService();
		jogoDAO.conectar();
		//pega qual a jogada atual pra saber qual o jogador
		Jogo jogo = jogoDAO.getJogo(request.session().attribute("jogo"));
		
		
		while(!tabuleiro.jogada(jogo, coluna));
		
		jogo.setJogada(jogo.getJogada() + 1);
		
		
		//faz a jogada passando a coluna e a jogada
		//
		/*
		 * Tem que adicionar mais um às jogadas
		 * 
		 * */
		jogoDAO.update(jogo);
		
		jogoDAO.close();
		
		if(jogo.getEstado() != 0) {
			System.out.println("O jogador " + jogo.getEstado() + " é o campeão");
			response.redirect("./fim.html");
			return null;
		}
		
		response.redirect("./index.html");
		
		return null;
	}
	
	
}

