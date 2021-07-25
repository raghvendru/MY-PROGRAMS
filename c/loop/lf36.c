#include <stdio.h>
int main(int argc,char*argv[])
{
    int a, b, c, i, series;
    printf("Enter series: ");
    scanf("%d", &series);
    a = 0;
    b = 1;
    c = 0;
    printf("Fibonacci terms: \n");
    for(i=1; i<=series; i++)
    {
        printf("%d, ", c);
     a = b;     
        b = c;     
        c = a + b; 
    }

    return 0;
}