#include <stdio.h>
int maxDiff(int arr[], int n){
   // Maximum difference found so far
   int MD = arr[1] - arr[0];
   // Minimum number visited so far
   int min = arr[0];
   for(int i = 1; i < n; i++){
      if (arr[i] - min > MD)
         MD = arr[i] - min;
      if (arr[i] < min)
         min = arr[i];

   }
   return MD;
}
/* Driver program to test above function */
int main(){
   int arr[] = {2,5,7,3,4,12};
   int n=6;
   // Function calling
   printf("Maximum difference is : %d ",maxDiff(arr, n));
   return 0;
}
/*

Approach used in the below program is as follows
Declare an array of integers which contains pairs of sides of rectangle.( Arr[] )

Create a variable to store the size of the array. (n)

The function maxArea(int arr[],int n) is used to calculate the maximum area for rectangle. It takes an input array and its size as arguments.

Inside maxArea() we declared an array Dim[2] two store highest two sides found after traversing the sorted array (in descending order) arr[].

As arr[] is sorted in descending order the highest 4 sides must be in the beginning. We will iterate arr[] such that a pair of sides is found.

Initialize Dim[] with 0 at first.

Inside the while loop we put the condition that it continues till j<2 that there are no values found for dim[0] and dim[1] or the end of arr[] is reached. (i<n).

If a pair of such sides is found, ( if(arr[i]==arr[i+1]) ), then store it in dim[j] and increment j for next side.

Return the result as a product of dim[0] and dim[1].

Note − sort(arr,n) is supposed to sort arr in descending order.

*/