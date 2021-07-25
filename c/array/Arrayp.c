#include <stdio.h>
 
// Function to find the majority element present in a given array
int findMajorityElement(int A[], int n)
{
    // `m` stores the majority element (if present)
    int m;
 
    // initialize counter `i` with 0
    int i = 0;
 
    // do for each element `A[j]` in the array
    for (int j = 0; j < n; j++)
    {
        // If counter `i` becomes 0, set the current candidate
        // to `A[j]` and reset the counter to 1
        if (i == 0) {
            m = A[j], i = 1;
        }
 
        // If the counter is non-zero, increment or decrement it
        // according to whether `A[j]` is a current candidate
        else {
            (m == A[j]) ? i++ : i--;
        }
    }
 
    return m;
}
 
int main(void)
{
    // assumption: valid input (majority element is present)
    int arr[] = { 1, 8, 7, 4, 1, 2, 2, 2, 2, 2, 2 };
    int n = sizeof(arr)/sizeof(arr[0]);
    int res;
    res = findMajorityElement(arr, n);
    printf("The majority element is %d", res);
 
    return 0;
}