#include<stdio.h>
 

int main()
{
    int n,m=0,l=0;
    printf("Type the length of the array\n");
    scanf("%d",&n);
    int array[n];
    printf("type the elements of the array\n");
    for(int i=0;i<n;i++){
        scanf("%d",&array[i]);
        }
 
    
    int largest=array[0];
    for(int i=0;i<n;i++)
    {
        int sum=0;
        for(int j=i;j<n;j++)
        {
            sum=sum+array[j];
            if(sum>largest)
            {
                m=i;l=j;
                largest=sum;
            }
        }
    }
    
 
    printf("largest contigous subarray is \n");
    for(int k=m;k<=l;k++)
    {
        printf("%d",array[k]);
    }
    printf("sum of largest contigous subarray is\n");
    printf("%d",largest);
    return 0;
}