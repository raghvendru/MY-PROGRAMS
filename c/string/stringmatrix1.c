#include <stdio.h>
#include<stdlib.h>

char filllet(char a[10][10],int c,int n);
{
    int i,j;
    char A='A';
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            a[i][j]=A+rand()%27;
        }
    }
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            filllet(a[10][10],c,n);
    
}







int main(){
    int a[10][10];
    char A='A';
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            a[i][j]=A+rand()%27;
        }
    }
    for(int i=0;i<10;i++){
        for(int j=0;j<10;j++){
            printf("%c",a[i][j]);
        }
        printf("\n");
    }
}
