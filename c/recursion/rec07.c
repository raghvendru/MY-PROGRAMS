#include<stdio.h>
int number(int num){
if(num==0){
    return 0;
}
return 1+number(num/10);
}
int main(){
    int n,count;
    printf("enter the number");
    scanf("%d",&n);
    printf("the number is %d\n",n);
    count=number(n);
    printf("the number of digit %d\n",count);
    return 0;
}