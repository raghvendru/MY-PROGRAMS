#include<stdio.h>
int main()
{
    int min,max,i,fact,j;
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
            printf("%d " ,i);
    }
    return 0;
}