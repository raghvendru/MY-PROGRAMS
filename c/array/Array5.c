#include<stdio.h>
void findpair(int a[],int n,int sum)
{
    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            if (a[i]+a[j]==sum){
                printf("pair found in %d,%d",i,j);
                return;
            }
        }
    }
    printf("not found\n");
}

int main()
{
    int a[]={8,7,2,5,3};
    int sum=10;
    int n=6;
    findpair(a,n,sum);
    return 0;

}