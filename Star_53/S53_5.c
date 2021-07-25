/**
 * C program to print box number pattern of 1's with 0 as center
 */

#include <stdio.h>

int main()
{
    int rows, cols, i, j;
    int centerRow, centerCol;

    /* Input rows and columns from user */
    printf("Enter number of rows: ");
    scanf("%d", &rows);
    printf("Enter number of columns: ");
    scanf("%d", &cols);

    /* Find center row and column */
    centerRow = (rows + 1) / 2;
    centerCol = (cols + 1) / 2;

    for(i=1; i<=rows; i++)
    {
        for(j=1; j<=cols; j++)
        {
            if(centerCol == j && centerRow == i)
            {
                printf("0");
            }
            else if(cols%2 == 0 && centerCol+1 == j)
            {
                if(centerRow == i || (rows%2 == 0 && centerRow+1 == i))
                    printf("0");
                else
                    printf("1");
            }
            else if(rows%2 == 0 && centerRow+1 == i)
            {
                if(centerCol == j || (cols%2 == 0 && centerCol+1 == j))
                    printf("0");
                else
                    printf("1");
            }
            else
            {
                printf("1");
            }
        }

        printf("\n");
    }

    return 0;
}