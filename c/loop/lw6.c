#include <stdio.h>

int main(int argc,char*argv[])

{
    int i, n, sum=0;

   
    printf("Enter num: ");
    scanf("%d", &n);
    i=1;

    
    while(i<=n)
    {
        sum += i;
    }

    printf("Sum of first %d natural numbers = %d", n, sum);
    i++;

    return 0;
}