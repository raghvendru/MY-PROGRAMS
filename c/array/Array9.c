#include<stdio.h>
int main()
{
    int i;
    int a[] = {1,2,3,4,5,6,7,9,10};
    int n = 10;
    int total=0,sum=0;
    total = (n*(n+1))/2; //to find sum of n no (natural no)
    for(i=0 ; i<n; i++){
       sum = sum + a[i];
    }
    printf("Missing number is %d",total-sum);   //a[i]=tot-sum
    return 0;
}