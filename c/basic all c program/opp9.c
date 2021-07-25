#include <stdio.h>

int main(int argc,char*argv[]) {
    int a;
    int b;

    printf("enter value of a,b \n ");
    scanf("%d %d",&a,&b);
    printf("final value of a is %d \n",a);
    printf("final value of a is %d \n",a>>1);
    printf("final value of a is %d \n",a>>b);
    printf("final value of a is %d \n",a<<b);
    printf("final value of a is %d \n",a&b);
    printf("final value of a is %d \n",a||b);
    return 0;


}