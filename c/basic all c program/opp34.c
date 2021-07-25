#include<stdio.h>

int main(int argc,char*argv[])
{
int a,b,c;
printf("enter a ,b and c \n");
scanf("%d,%d",&a,&b,&c);
printf("value is %d \n",a<<b);
printf("enter a ,b and c \n");
scanf("%d,%d",&a,&b,&c);
printf("value is %d \n",a>>b);
}