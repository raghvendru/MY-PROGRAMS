#include<stdio.h>
int main()
{
    int min,max,i,fact,j,sum;
    printf("Enter the min n max num");
    scanf("%d %d",&min,&max);
    printf("Prime Numbers are: \n");
    for(i=min; i<=max; i++)
    {
        fact=0;
        for(j=1; j<=i; j++)
        {
            if(i%j==0)
                fact++;
               
        }
        if(fact==2)
     
        {
             //printf("%d \n",i);
            sum += i;
        }
    }

    printf("Sum of all prime numbers between 1 to %d = %d",i,  sum);

    return 0;
}
