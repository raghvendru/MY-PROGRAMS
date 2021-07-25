/**
 * C program to print the given number pattern
 */

#include <stdio.h>

int main()
{
    int i, j, N;

    printf("Enter rows: ");
    scanf("%d", &N);

    for(i=1; i<=N; i++)
    {
        // Prints first part of pattern
        for(j=1; j<=i; j++)
        {
            printf("%d", j);
        }

        // Prints spaces between two parts
        for(j=i*2; j<N*2; j++)
        {
            printf(" ");
        }

        // Prints second part of the pattern
        for(j=i; j>=1; j--)
        {
            printf("%d", j);
        }

        printf("\n");
    }

    return 0;
}