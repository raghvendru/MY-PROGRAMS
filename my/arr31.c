#include <stdio.h>
 
// Function to find the ceil of `x` in a sorted array `arr[0…n-1]`
// i.e., the smallest integer greater than or equal to `x`
int getCeil(int arr[], int n, int x)
{
    // search space is `A[low…high]`
    int low = 0, high = n - 1, mid;
 
    // initialize ceil to -1
    int ceil = -1;
 
    // loop till the search space is exhausted
    while (low <= high)
    {
        // find the mid-value in the search space
        mid = (low + high) / 2;
 
        // if `x` is equal to the middle element, it is the ceil
        if (arr[mid] == x) {
            return arr[mid];
        }
 
        // if `x` is less than the middle element, the ceil exists in the
        // subarray `A[low…mid]`; update ceil to the middle element
        // and reduce our search space to the left subarray `A[low…mid-1]`
        else if (x < arr[mid])
        {
            ceil = arr[mid];
            high = mid - 1;
        }
 
        // if `x` is more than the middle element, the ceil exists in the
        // right subarray `A[mid+1…high]`
        else {
            low = mid + 1;
        }
    }
 
    return ceil;
}
 
// Function to find the floor of `x` in a sorted array `arr[0…n-1]`,
// i.e., the largest integer less than or equal to `x`
int getFloor(int arr[], int n, int x)
{
    int low = 0, high = n - 1, mid;
 
    // initialize floor to -1
    int floor = -1;
 
    // loop till the search space is exhausted
    while (low <= high)
    {
        // find the mid-value in the search space
        mid = (low + high) / 2;
 
        // if `x` is equal to the middle element, it is the floor
        if (arr[mid] == x) {
            return arr[mid];
        }
 
        // if `x` is less than the middle element, the floor exists in the left
        // subarray `A[low…mid-1]`
        else if (x < arr[mid]) {
            high = mid - 1;
        }
 
        // if `x` is more than the middle element, the floor exists in the
        // subarray `A[mid…high]`; update floor to the middle element
        // and reduce our search space to the right subarray `A[mid+1…high]`
        else {
            floor = arr[mid];
            low = mid + 1;
        }
    }
 
    return floor;
}
 
int main(void)
{
    int arr[] = { 1, 4, 6, 8, 9 };
    int n = sizeof(arr) / sizeof(arr[0]);
 
    for (int i = 0; i <= 10; i++)
    {
        printf("Number %d —> ", i);
        printf("ceil is %d, ", getCeil(arr, n, i));
        printf("floor is %d\n", getFloor(arr, n, i));
    }
 
    return 0;
}

