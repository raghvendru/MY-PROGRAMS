

#include <stdio.h>
int main()
{
   int n, i, prod = 1, remainder;

   printf("Enter an integer\n");
   scanf("%d", &n);

   i = n;

   while (i != 0)
   {
      remainder = i % 10;
      prod       = prod * remainder;
      i         = i / 10;
   }

   printf("product of digits of %d = %d\n", n, prod);

   return 0;
}