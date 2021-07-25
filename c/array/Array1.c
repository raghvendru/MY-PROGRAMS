#include<stdio.h>
int main()
{
int n;
int i, sum = 0;
int arr[n];
printf("enter size");
scanf("%d",&n);
printf("enter the elemnts");
for(i = 0; i < n; i++)
{
scanf("%d,&arr[i]");
}
for(i = 0; i < n; i++)
sum = sum + arr[i];
printf("sum is %d",sum);
return 0;
}