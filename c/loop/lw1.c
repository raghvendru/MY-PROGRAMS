#include <stdio.h>

int main(int argc,char*argv[])
{
int n ;
printf("enter the number n\n");
printf("natura no 1 to n is \n",n);
scanf("%d",&n);
int i=1;
while (i<=n) {
    printf("%d \n",i);
    i++;
}
}