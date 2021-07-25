#include <stdio.h>
int main()
{
   int n, i, sum = 0, remainder;

   printf("Enter an integer\n");
   scanf("%d", &n);

   i = n;

   while (i != 0)
   {
      remainder = i % 10;
      sum       = sum + remainder;
      i         = i / 10;
   }

   printf("Sum of digits of %d = %d\n", n, sum);

   return 0;
}