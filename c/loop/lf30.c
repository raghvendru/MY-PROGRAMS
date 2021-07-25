#include <stdio.h>

int main(int argc,char*argv[])
{
int n,temp,rem,arms=0,digit;
int pow;
int temp1;
printf("enter no \n");
scanf("%d",&n);
temp=n;
temp1=n;
while(n>0)
{
    n=n/10;
    digit++;
}
while(temp>0){
    rem=temp%10;
    pow=1;
    for(int i=1;i<=digit;i++){
        pow=pow*rem;

    }
    printf("pow-%d\n",pow);
    arms=arms+pow;
    printf("arms=%d\n",arms);
    temp/=10;
    }
    printf("arms=%d/n",arms);
    if(temp1==arms){
        printf("given no %dis armstrong no",temp1);
    }
    else {
        printf("given no %dis not armstrong no",temp1);}

    }
    