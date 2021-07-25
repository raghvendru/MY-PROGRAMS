/**
 * C program to print Floyd number pattern
 */

#include <stdio.h>

int main()
{
    int i, j, k, N;

    printf("Enter N: ");
    scanf("%d", &N);

    k = 1;

    for(i=1; i<=N; i++)
    {
        // Logic to print numbers
        for(j=1; j<=i; j++, k++)
        {
            printf("%3d", k);
        }

        printf("\n");
    }

    return 0;
}