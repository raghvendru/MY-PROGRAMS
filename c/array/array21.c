//whose sum closest to zero
#include <stdio.h>
#include <stdlib.h>
#include<math.h>


void minabsvaluepair(int arr[], int arr_size)
{
    int count=0;
    int l,r,min_sum,sum,min_l,min_r;
    min_l=0;
    min_r=1;
    min_sum=arr[0]+arr[1];
    for (l = 0;l<arr_size-1;l++)
    {
        for (r=l+1;r<arr_size;r++)
        {
            sum =arr[l]+arr[r];
            if (abs(min_sum)>abs(sum))
            {
                min_sum=sum;
                min_l=l;
                min_r=r;
            }
        }
    }
    printf("The sum is %d and %d",arr[min_l],arr[min_r]);
}

 
int main()
{
    int arr[] = {42,15,-25,30,-10,35};
    minabsvaluepair(arr, 6);
    return 0;
}