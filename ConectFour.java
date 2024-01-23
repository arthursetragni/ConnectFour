import java.util.*;

public class ConectFour{
    private Jogador[] jogadores;
    private int[][] tabuleiro;
    int rodada;
    int estado;
    ConectFour(){
        this.jogadores = new Jogador[2];
        this.tabuleiro = criaTabuleiro();
        this.rodada = 1;
        estado = 0;
    }
    private int[][] criaTabuleiro(){
        int[][] tabuleiro = new int[6][7];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                tabuleiro[i][j] = 0;
            }
        }
        return tabuleiro;
    }

    private void imprimeTabuleiro(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(tabuleiro[i][j] + " ");
            }
             System.out.print("\n");
        }

    }

    private void jogada(int coluna){
        int caracter;
        if(rodada % 2 == 0) caracter = 2;
        else caracter = 1;
        int i =5;
        while(tabuleiro[i][coluna] != 0){
            i--;
        }
        tabuleiro[i][coluna] = caracter;
        imprimeTabuleiro();
        rodada++;
        if(this.rodada > 6) temGanhador(i, coluna, tabuleiro[i][coluna]);
    }

    private void temGanhador(int fileira, int coluna, int conteudo){
        if(fileira < 3) vitoriaVertical(fileira, coluna, conteudo);
        vitoriaHorizontal(fileira, coluna, conteudo);
        
        vitoriaDiagonal(fileira, coluna, conteudo);
    }
    
    private void vitoriaVertical(int fileira, int coluna, int conteudo){
        int verificador = 0;
        int aux = fileira;
        while(aux<=5 && tabuleiro[aux][coluna] == conteudo){
            verificador++;
            aux++;
        } 
        if(verificador >= 4) estado = conteudo;
    }
    
    private void vitoriaHorizontal(int fileira, int coluna, int conteudo){
        int aux = coluna;
        while(aux >= 1 && tabuleiro[fileira][aux - 1] == conteudo){
            aux--;
        }

        int verificador = 0;

        while (aux <=6 && tabuleiro[fileira][aux] == conteudo) {
            aux++;
            verificador++;
        }
        if(verificador >= 4){
            estado = conteudo;
        }
    }

    private void vitoriaDiagonalDescendente(int fileira, int coluna, int conteudo) {
        while (fileira >= 1 && coluna <= 4 && tabuleiro[fileira - 1][coluna + 1] == conteudo) {
            fileira--;
            coluna++;
        }
        int verificador = 0;
        while (fileira <= 5 && coluna >= 0 && tabuleiro[fileira][coluna] == conteudo) {
            fileira++;
            coluna--;
            verificador++;
        }
        if (verificador >= 4) estado = conteudo;
    }
    
    private void vitoriaDiagonalAscendente(int fileira, int coluna, int conteudo) {
        while (fileira >= 1 && coluna >= 1 && tabuleiro[fileira - 1][coluna - 1] == conteudo) {
            fileira--;
            coluna--;
        }
        int verificador = 0;
        while (fileira <= 5 && coluna <= 4 && tabuleiro[fileira][coluna] == conteudo) {
            fileira++;
            coluna++;
            verificador++;
        }
        if (verificador >= 4) estado = conteudo;
    }
        
    private void vitoriaDiagonal(int fileira, int coluna, int conteudo) {
        vitoriaDiagonalDescendente(fileira, coluna, conteudo);
        vitoriaDiagonalAscendente(fileira, coluna, conteudo);
    }
    
    public static void main(String[] args){
        ConectFour jogo = new ConectFour();
        int numeroDVezes = 6 * 7;
        jogo.imprimeTabuleiro();
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < numeroDVezes; i++){
            
            int coluna = scanner.nextInt();
            jogo.jogada(coluna - 1);
            if(jogo.estado != 0) {
                System.out.println("O jogador " + jogo.estado + " ganhou!!");
                i = numeroDVezes;
            }
        }
         scanner.close();
    }
}