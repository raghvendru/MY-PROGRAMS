#include <stdio.h>

int main(int argc,char*argv[]) {
    char a;
    char b;
    char c;

    printf("enter value of a,b,c \n ");
    scanf("%c, %c,%c",&a,&b,&c);
    printf("final value of a is %d \n",a>b && a>c);
    return 1;


}