#include <stdio.h>

int main(int argc,char*argv[]) {
    char a,b;

    printf("enter two characters seperated by spaces:");
    scanf("%c %c",&a,&b);
    printf("%c=%d\n",a,a);
    printf("%c=%d\n",b,b);
    printf("%c+%c =%d%c \n",a,b,a+b,a+b);
    printf("%c+%c =%c \n",a,a+b);
    printf("%d-%d =%c \n",a,b,a-b);
    
}