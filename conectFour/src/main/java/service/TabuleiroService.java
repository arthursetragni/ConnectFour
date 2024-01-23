package service;

import dao.*;
import model.*;
public class TabuleiroService{
	private TabuleiroDAO tabuleiroDAO;
	
	public TabuleiroService() {
		tabuleiroDAO = new TabuleiroDAO();
	}
	
	public int create() {
		Tabuleiro tabuleiro = new Tabuleiro(1);
		
		//this.tabuleiroDAO = new TabuleiroDAO();
		
		tabuleiroDAO.connect();
		
		tabuleiroDAO.add(tabuleiro);
		
		tabuleiroDAO.close();
		
		return tabuleiro.getId();
	}
	
	/*public Tabuleiro get() {
		Tabuleiro tabuleiro = tabuleiroDAO.get();
		return tabuleiro;
	}*/
	
	
	//entrada do usuario ainda nao foi validada
	public boolean jogada(Jogo jogo,int coluna) {
		
		tabuleiroDAO.connect();
		
		int jogador = (jogo.getJogada() % 2) + 1;		
		
		Tabuleiro tabuleiro = tabuleiroDAO.get(jogo.getIdTabuleiro());
		
		//A propria função de inserir uma jogada verifica se tem ganhador e retorna o estado do jogo
		//0 = jogo ativo
		//1 = vitoria jogador 1
		//2 = vitoria jogador 2
		jogo.setEstado(tabuleiro.setValor(jogador, (coluna - 1), jogo.getJogada()));  
		
		tabuleiroDAO.update(jogo.getIdTabuleiro(), tabuleiro);
		
		tabuleiroDAO.close();
		
		return true;
	}
	
}