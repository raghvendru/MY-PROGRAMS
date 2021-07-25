#include <stdio.h>

void leader(int arr[], int n)
{
    for (int i=0;i<n;i++)
    {
        for (int j=i;j<n;j++)
        {
            if (arr[i]<arr[j])
            {
                break;
            }
            if (j==n-1) //last element is always leader
            printf("%d is a leader\n", arr[i]);
        }
    }
}

void main()
{
    int arr[]={ 7, 6, 4, 5, 0, 1 };
    int n=6;
    // calling function
    leader(arr, n);
}