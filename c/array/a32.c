#include <stdio.h>
#include <stdlib.h>
int main(void){
    int arr_size1;
    int nums1[] = {1, 5, 7, 9, 11, 13};
    arr_size1 = sizeof(nums1)/sizeof(nums1[0]);
    printf("Elements in original array1 are: ");  
    print_array(nums1, arr_size1);
    int result[arr_size1];
    for (int i = 0; i < arr_size1; i++) {
            result[i] = nums1[i];
             }   
    int first = nums1[0];
    result[0] = nums1[arr_size1 - 1];
    result[arr_size1 - 1] = first;

    printf("New array, after swapping first and last elements: ");  
    print_array(result, arr_size1);        
 }  
  print_array(int parray[], int size)
    {
     int i;      
     for( i=0; i<size-1; i++)  
      {  
        printf("%d, ", parray[i]);  
       } 
    printf("%d ", parray[i]);  
    printf("\n");   
  } 