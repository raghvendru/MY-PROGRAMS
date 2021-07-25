#include<stdio.h>
int sum(int n){
if(n==0){
    return 0;
}
    return  n%10+sum(n/10);
}
int count(int n){
    if(n==0){
    return 0;
 }
    return 1+ count(n/10);

}
   

int main(){
    int a;
    printf("enter the number ");
    scanf("%d",&a);
    printf("the average of  %d\n",a,sum(a)*1.0/count(a));
}