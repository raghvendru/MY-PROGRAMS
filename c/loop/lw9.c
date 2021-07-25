#include <stdio.h>

int main(int argc,char*argv[])
{
    int n, i;
    printf("Enter an integer: ");
    scanf("%d", &n);
    i = 1;
    while ( i <= 10) {
        printf("%d * %d = %d \n", n, i, n * i);
        ++i;
    }
    return 0;
}