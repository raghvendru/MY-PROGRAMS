#include <stdio.h>
int main(int argc,char*argv[])
{
    int i, original, num, last, sum;
    long fact;
    printf("Enter any number ");
    scanf("%d", &num);
    original = num;
    sum = 0;

    while(num > 0)
    {

        last = num % 10;
        fact = 1;
        for(i=1; i<=last; i++)
        {
            fact = fact * i;
        }
         sum = sum + fact;
         num = num / 10;
    }
    if(sum == original)
    {
        printf("%d is STRONG NUMBER", original);
    }
    else
    {
        printf("%d is NOT STRONG NUMBER", original);
    }

    return 0;
}