#include <stdio.h>
int main()
{ 
   int first,second,*p,*q,sum;

   printf("Enter two integers to add\n");
   scanf("%d%d",&first,&second);

   p=&first;
   q=&second;

   sum =*p+*q;

   printf("Sum of the numbers=%d\n",sum);
   printf("Sum of the numbers=%d\n",*p+*q);
   printf("First interger address=%p\n",p);
   printf("Second interger address= %p\n", q);
   printf("First interger = %d\n",*p);
   printf("Second interger = %d\n",*q);   
   return 0;
}