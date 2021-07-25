

#include <stdio.h>
int main() {
   int l,b;
   printf("Enter l amd b year: ");
   scanf("%d %d", &l,&b);

   if (l==b) {
      printf("its a square",l,b);
   }
   
   else  {
      printf("its rectangle",l,b);
   }

   return 0;
}