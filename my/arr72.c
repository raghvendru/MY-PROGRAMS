#include <stdio.h>
 
// Find odd occurring elements in a given array
void findRepeating(int A[], int n)
{
    int xor = 0;
    for (int i = 0; i < n; i++) {
        xor ^= (1 << A[i]);
    }
 
    printf("The odd occurring elements are ");
    for (int i = 0; i < n; i++)
    {
        if (xor & (1 << A[i]))
        {
            printf("%d ", A[i]);
            xor ^= (1 << A[i]);     // to avoid printing duplicates
        }
    }
}
 
int main(void)
{
    int A[] = { 5, 8, 2, 5, 8, 2, 8, 5, 1, 8, 2 };
    int n = sizeof(A) / sizeof(A[0]);
 
    findRepeating(A, n);
 
    return 0;
}






