
 #include<stdio.h>
int main()
 
 {
   int a,b,c;
   printf("\nType the ages of a,b,c:");
   
   scanf("%d %d %d",&a,&b,&c);
   if ((a>b) && (a>c))
   {
       printf("\nThe biggest age is A");
   }
   else
   {
       printf("\nThe lesser age is A");
   }
   if ((b>a) && (b>c))
   {
       printf("\nThe biggest age is B");
   }
   else
   {
       printf("\nThe lesser age is B");
   }
   if ((c>a) && (c>b))
   {
       printf("\nThe biggest age is C");
   }
   else
   {
       printf("\nThe lesser age is C");
   }

 }