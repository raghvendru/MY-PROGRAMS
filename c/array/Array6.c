#include<stdio.h>
int findmej(int a[],int n)
{
    int m;
    int i=0;
    for(int j=0;j<n;j++){
        if(i==0){
            m=a[j],i=1;
        }
        else{
            (m==a[j] ? i++ : i--);
        }
    }
    return m;
}

int main()
{
    int a[]={1,8,2,2,8,2};
    int n=6;
    int res;
    res=findmej(a,n);
    printf("mej ele is %d",res);
    return 0;

}