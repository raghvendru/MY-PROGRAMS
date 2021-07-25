#include <stdio.h>

int main(int argc,char*argv[]) {
    int a;
    int b;
    int c;

    printf("enter value of a,b,c \n ");
    scanf("%d %d %d",&a,&b,&c);
    printf("final value is %d \n",a>b && a>c);
    return 1;


}