#include<stdio.h>
int countdigit(int num)
{
static int count=0;
    if(num>0)
    {

        if(num%10==0)
        count++;

        countdigit(num/10);
    }
    return count;
}
int main()
{
    int n;
    printf("Enter a number");
    scanf("%d",&n);
    printf("The number of Zeros in the Given Number is %d",countdigit(n));
}
