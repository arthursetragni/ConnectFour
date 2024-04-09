#include <stdio.h>
#include<stdlib.h>

int main(){
    int entrada = 1;
    while(entrada > 0){
        scanf("%d", &entrada);
        int* vetor = malloc(entrada * sizeof(int));
        for(int i = 0; i < entrada; i = 0){
            vetor[i] = i + 1;
        }
        int tamanho = entrada - 1;
        

    }
    return 0;
}