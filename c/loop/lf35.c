 #include <stdio.h>
int main(int argc,char*argv[])
{
    int min,max,n,r,fact,sum,i,strong,j;
    printf("enter min max no\n");
    scanf("%d %d",min,max);
    for(i=min;i<=max;i++) {
        n=i;
        strong=n;
        sum=0;
        while(n!=0) {
            r=n%10;
            fact=1;
        for(j=1;j<=r;j++) {
            fact=fact*j;
            }
            sum=sum*fact;
            n=n/10;
        }
        }
      if(sum==strong) {
        printf("%d \n",strong);
    
    }
}