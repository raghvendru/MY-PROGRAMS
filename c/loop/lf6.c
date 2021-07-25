#include <stdio.h>

int main(int argc,char*argv[])

{
    int i, n, sum=0;

   
    printf("Enter num: ");
    scanf("%d", &n);

    
    for(i=1; i<=n; i++)
    {
        sum += i;
    }

    printf("Sum of first %d natural numbers = %d", n, sum);

    return 0;
}