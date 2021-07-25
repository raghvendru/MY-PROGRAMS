#include<stdio.h>

//fun declare
void findtriplets(int arr[],int n,int sum)
{
    for (int i=0;i<n-2;i++){
        for (int j=i+1;j<n-1;j++){
            for (int k=j+1;k<n;k++){
                if (arr[i]+arr[j]+arr[k]==sum){
                    printf("%d,%d,%d\n",arr[i],arr[j],arr[k]);
                }
            }
        }
    }
}


int main(){
int n, sum;
printf("Enter number of elements\n");
scanf("%d",&n);
int arr[n];
printf("enter array elements\n");
for(int i = 0; i < n; i++)
{
scanf("%d",&arr[i]);
}
printf("\nEnter the sum value");
scanf("%d",&sum);
printf("The triplets are\n");
findtriplets(arr, n, sum);
return 0;
}