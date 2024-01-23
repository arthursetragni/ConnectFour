#include<stdio.h>
#include<stdlib.h>
#include <time.h>

int** criaTabuleiro(){
    int** linhas = malloc(6 * sizeof(int*));
    for(int i = 0; i < 6; i++){
        linhas[i] = malloc(7 * sizeof(int));
    }
    for(int i = 0; i < 6; i++){
        for(int j = 0; j < 7; j++){
            linhas[i][j] = 0;
        }
    }
    return linhas;
}

void destroiTabuleiro(int ** tabuleiro){
    for(int i = 0; i < 6; i++){
        free(tabuleiro[i]);
    }
    free(tabuleiro);
}

void jogada(int** tabuleiro, int coluna, int* rodada){
    int caracter;
    if(*rodada % 2 == 0) caracter = 2;
    else caracter = 1;
    int i =5;
    while(tabuleiro[i][coluna] != 0){
        i--;
    }
    tabuleiro[i][coluna] = caracter;
    imprimeTabuleiro();
    *rodada++;
    if(*rodada > 6) temGanhador(i, coluna, tabuleiro[i][coluna]);
}

int main(){
    char nomeDoArquivo[] = "tabela.csv";
    FILE *arquivo = fopen(nomeDoArquivo, "w");
    fprintf(arquivo, "Tabuleiro,Acao,AcaoOponente,Resultado\n");
    int rodada;
    for(int i = 0; i < 1000000; i++){
        rodada = 1;
        int numDRodadas =  rand() % 42 + 1; 
        int coluna = rand() % 7 + 1;
        int** tabuleiro = criaTabuleiro();

        jogada(tabuleiro, coluna, &rodada);

        destroiTabuleiro(tabuleiro);
    }
    
    // for(int i = 0; i < 6; i++){
    //     for(int j = 0; j < 7; j++){
    //         printf("%d ", tabuleiro[i][j]);
    //     }
    //     printf("\n");
    // }

    
    return 0;
}