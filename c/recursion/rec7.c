#include<stdio.h>
int countdigit(int num)
{
static int count;
    if(num!=0)
    {          
        countdigit(num/10);
            count++;
        
    }
    return count;
}
int main()
{
    int n;
    int count=0;
    printf("Enter a number\n");
    scanf("%d",&n);
    count=countdigit(n);
    printf("The number of digits in the Given Number is %d\n",count);
}
