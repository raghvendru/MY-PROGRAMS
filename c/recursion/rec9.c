#include <stdio.h> 

//declaring function
int countdigits(int n, int k) 
{ 
  if (n==0)  //base
  return 0;
  int digit=n%10;  //comp and decomp
  if (digit==k)    //if digit equ to entered num then increase count
  return 1+countdigits(n/10, k); 
  return countdigits(n/10, k); 
} 

//calling function
int main() 
{ 
int n=1000;
int k=0; 
int c;
c=countdigits(n, k);
printf("%d \n",c);
} 