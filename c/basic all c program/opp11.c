#include <stdio.h>

int main(int argc,char*argv[]) {
    int a;
    int b;

    printf("enter value of a \n ");
    scanf("%d",&a);
    printf("final value of a is %d \n",a++);
    printf("final value of a is %d \n",++a);
    printf("final value of a is %d \n",a--);
    printf("final value of a is %d \n",--a);
    return 1;
}