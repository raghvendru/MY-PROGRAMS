#include <stdio.h>
int add(int *x,int *y){
   int sum;
   sum=*x+*y;
   return sum;
}

int sub(int *x,int *y){
   int sub;
   sub=*x-*y;
   return sub;
}


int add(int *,int *);
int sub(int *,int *);
int main()
{
   int first,second,*p,*q,sum,subtract;
   printf("enter two integers to add\n");
   scanf("%d%d",&first,&second);
   sum =add(&first,&second);
   subtract=sub(&first,&second);
   printf("(%d)+(%d)=(%d)\n",first,second,sum);
   printf("(%d)-(%d)=(%d)\n",first,second,subtract);
   return 0;
}

