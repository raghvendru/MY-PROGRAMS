#include<stdio.h>
int arraysearch(int a[],int n,int x)
{
    int low=0;
    int high=n-1;

    while(low<=high){
        int mid=(low+high)/2;
        if(x==a[mid]) //got x
        return mid;

        if(a[mid]<=a[high]){  //right half sorted
            if(x>a[mid] && x<=a[high]) //searching in right sorted half
            low=mid+1;
            else
            high=mid-1; //searching left
        }


        else   //left half sorted
        {
            if(a[low]<=x && x<a[mid]) //searching left sorted array
            high=mid-1;
            else
            low=mid+1; //searching right
        }
    }
    return -1; //not found
}



int main()
{
    int a[8]={12,14,18,21,3,6,8,9};
    int x;
    printf("enter no");
    scanf("%d",&x);
    int index=arraysearch(a,8,x);
    if(index==-1){
        printf(" not  found");
    }
    else{
        printf("found at %d" ,x,index);
    }
    
}



