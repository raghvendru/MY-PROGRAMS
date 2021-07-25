#include <stdio.h>

int sum(int *x,int *y) {
   int sum;
   sum=*x+*y;
   return sum;
}

int sum(int *,int *);
int main()
{
   int first,second,*p,*q,sum1;
   printf("Input two integers to add\n");
   scanf("%d%d",&first,&second);
   sum1=sum(&first,&second);
   printf("(%d)+(%d)=(%d)\n",first,second,sum1);
   return 0;
}