#include <stdio.h>

int main(int argc,char*argv[])
{
int n ;
printf("enter the number n\n");
printf("natura no 1 to n is \n",n);
scanf("%d",&n);
for(int i=n;i>0;i--) {
    printf("%d \n",i);
}
}