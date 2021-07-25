#include<stdio.h>
int fact(int n);
int main() {
    int n=3;
    printf("%d is %d", n, n*fact(n-1));
    return 0;
}

int fact(int n) {
    if (n==1){
        return 1;}
    else{
        return n*fact(n-1);}
}