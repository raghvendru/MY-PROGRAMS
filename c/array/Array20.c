#include <stdio.h>
 
// Function to find inversion count of a given array
int findInversionCount(int arr[], int n)
{
    int inversionCount=0;
    for (int i=0;i<n-1;i++)   //i<j to ensure this we used i n j like this
    {
        for (int j=i+1;j<n;j++)
        {
            if (arr[i]>arr[j]) {
                inversionCount++;
            }
        }
    }
    return inversionCount;
}
 
int main()
{
    int arr[]={ 1, 9, 6, 4, 5 };
    int N=5;
    printf("The inversion count is %d", findInversionCount(arr, N));
    return 0;
}


