#include <stdio.h>
#include <math.h>

int main()
{
    int n, t,t1, remainder, result = 0, count= 0 ;

    printf("Enter an integer: ");
    scanf("%d", &n);

     t = n;
     t1=n;

    while (n != 0)
    {
        n /= 10;
        ++count;
    }

   

    while (t != 0)
    {
        remainder = t%10;
        result += pow(remainder, count);
        t /= 10;
    }

    if(result == t1)
        printf("%d is an Armstrong number.", t1);
    else
        printf("%d is not an Armstrong number.", t1);

    return 0;
}