#include <stdio.h>
void swap(int *a,int *b)
{
   int t;

   t=*b;
   *b=*a;
   *a=t;
}


void swap(int*,int*);
int main()
{
   int x,y;

   printf("enter the value of x and y\n");
   scanf("%d%d",&x,&y);

   printf("before Swapping x=%d y=%d\n",x,y);

   swap(&x,&y);

   printf("after Swapping x=%d y=%d\n",x,y);
   return 0;
}


