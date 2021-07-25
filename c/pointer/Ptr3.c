#include <stdio.h>

int sum(int *x,int *y) {
   int sum;
   *x=(*x)*2;
   *y=(*y)*2;
   sum=*x+*y;
   return sum;
}

int sum(int *,int *);
int main()
{
   int first,second,*p,*q,sum1;
 
   printf("enter two integers to add\n");
   scanf("%d%d",&first,&second);
   printf("First num before modification %d\n",first);
   printf("Second num before modification %d\n",second);
   
   sum1=sum(&first,&second);
   
   printf("First num after modification %d\n",first);
   printf("Second numb after modification %d\n",second);
 
   printf("(%d)+(%d)=(%d)\n",first,second,sum1);
   return 0;
}

