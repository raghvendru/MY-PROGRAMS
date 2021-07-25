#include <stdio.h>
int globalint=100;
char globalChar='g';

int main() {
    int localint=10;
    long localLong=23456789;
    double localdouble=2.3;
    char localChar='A';
    char localString[20]="Learn";
    int a,b;
    //now lets print the adress and the value of each variable
    printf("globalInt address is %p and value is %d,%d \n",&globalint,globalint,sizeof(int));
   printf("globalChar address is %p and value is %c,%d \n",&globalChar,globalChar,sizeof(char));
   printf("====Global Variable Over====\n");
   printf("localChar address is %p and value is %c,%d\n",&localChar,localChar,sizeof(char));
   printf("localDouble address is %p and value is %lf,%d\n",&localdouble,localdouble,sizeof(double));
   printf("localLong address is %p and value is %lu,%d\n",&localLong,localLong,sizeof(long));
    printf("localint address is %p and value is %d,%d\n",&localint,localint,sizeof(int));
     printf("localString address is %p and value is %d,%d\n",&localString,localString,sizeof(localString));
}  


























