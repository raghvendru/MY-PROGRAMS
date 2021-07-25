 #include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    int a[10][10];
    char A='A';
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            a[i][j]=A+rand()%26; //to get a to z(0 to 25)
        }
    }
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            printf("%c ",a[i][j]);
        }
        printf("\n");
    }

    char words[5][10] = {"raghu","ranjan","advik","arun","anup"} ;
    for(int i=0;i<5;i++){
        printf("%s ",words[i]);
        // now place this word in the matrix
        // get random rows
        int row = rand() % 10 ;
        // get random col 
        int col = rand() % (10 - strlen(words[i])) ;
        printf("rows is %d and col is %d \n",row, col);
        for (int j = 0;j<strlen(words[i]);j++) {
            a[row][col+j] = words[i][j];
        }
    }   
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            printf("%c ",a[i][j]);
        }
        printf("\n");
    }

}
