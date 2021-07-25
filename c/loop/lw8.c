#include <stdio.h>

int main(int argc,char*argv[])

{
    int i, n, sum=0;

   
    printf("Enter num: ");
    scanf("%d", &n);

    i=1;
    while( i<=n)

     if(i%2!=0)
{

    {
        sum += i;
    }
      i++;
}

    printf("Sum of first %d odd numbers = %d", n, sum);
  

    return 0;
}