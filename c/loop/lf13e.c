#include <stdio.h>
int main(int argc, char *argv[]) {
    int n ;
    int temp=n;
    int numofd=0;
    int maxmult=0;
    int result=0;
    printf("swap first and last digits of a number\n") ;
    printf("Enter the number: \n");
    scanf("%d", &n) ;
    while(n>0) {
        n=n/10;
        numofd=numofd+1;
        if(maxmult==0)
         maxmult=1;
         else 
         maxmult=maxmult*10;
         prntf("%d %d\n",numofd,maxmult);

    }
    prntf("%d %d %d\n",n,temp,numofd,maxmult);
