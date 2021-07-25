
// C program to sort a binary array in one pass 
#include<stdio.h> 
  
/*Function to put all 0s on left and all 1s on right*/
void segregate0and1(int arr[], int size) 
{ 
    /* Initialize left and right indexes */
    int left = 0, right = size-1; 
  
    while (left < right) 
    { 
        /* Increment left index while we see 0 at left */
        while (arr[left] == 0 && left < right) 
            left++; 
  
        /* Decrement right index while we see 1 at right */
        while (arr[right] == 1 && left < right) 
            right--; 
  
        /* If left is smaller than right then there is a 1 at left 
          and a 0 at right.  Exchange arr[left] and arr[right]*/
        if (left < right) 
        { 
            arr[left] = 0; 
            arr[right] = 1; 
            left++; 
            right--; 
        } 
    } 
} 
  
/* driver program to test */
int main() 
{ 
    int arr[] = {0, 1, 0, 1, 1, 1}; 
    int i, arr_size = sizeof(arr)/sizeof(arr[0]); 
  
    segregate0and1(arr, arr_size); 
  
    printf("Array after segregation "); 
    for (i = 0; i < 6; i++) 
        printf("%d ", arr[i]); 
    return 0; 
} 