#include <stdio.h>
int main(int argc,char*argv[])
{
    int i, j, num, sum;
    printf("Enter num ");
    scanf("%d", &num);
    printf("All Perfect numbers between 1 to %d:\n",num );
    for(i=1; i<=num; i++)
    {
        sum = 0;

   
        for(j=1; j<i; j++)
        {
            if(i % j == 0)
            {
                sum += j;
            }
        }
 
        
        if(sum == i)
        {
            printf("%d, ", i);
        }
    }

    return 0;
}