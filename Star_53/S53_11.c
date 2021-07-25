/**
 * C program to print the given number pattern
 */

#include <stdio.h>

int main()
{
    int i, j, value, N;

    printf("Enter rows: ");
    scanf("%d", &N);

    for(i=1; i<=N; i++)
    {
        value = i + 1;
        // Prints the first part of pattern
        for(j=3; j<=i; j++)
        {
            printf("%d ", value);
            value++;
        }

        // Prints the second part of pattern
        for(j=(i*2)-1; j>=i; j--)
        {
            printf("%d ", j);
        }

        printf("\n");
    }

    return 0;
}