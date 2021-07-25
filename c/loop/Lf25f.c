#include<stdio.h>

int main() {
   int a, b, i, hcf,lcm;

   printf("enter two num \n");
   scanf("%d %d",&a,&b);
int least=a > b ? b : a ;
int cnt=least;
for(int i=1;i<=cnt;i++) {
    if (a%i==0 && b%i==0)
    hcf=i;
}
printf("hcf is %d \n",hcf);
lcm=a*b/hcf;
printf("lcm is %d\n",lcm);
}
