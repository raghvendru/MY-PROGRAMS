#include<stdio.h>
 
int main() {
   int n1=6;
   int n2=5;
   int c;
   c = mult(n1, n2);
 
   printf("multiplication of two number is");
   return (0);
}
 
int mult(int a, int b) {
   int c;
   c = a*b;
   return (c);
}