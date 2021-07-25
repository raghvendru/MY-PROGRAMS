#include <stdio.h>
 
int maxSumSubseq(int arr1[], int n)
{
  int incl=arr1[0];
  int excl=0;
  int excl_new;
  int i;
  for (i = 1; i < n; i++)
  {
     excl_new = (incl > excl)? incl: excl;
 
     incl = excl + arr1[i];
     excl = excl_new;
  }
   return ((incl > excl)? incl : excl);

}
 
int main()
{
   int arr1[] = {1, 3, 5, 9, 7, 10, 1, 10, 100};
     int n = sizeof(arr1) / sizeof(arr1[0]);
     int i;
	printf("The given array is :  ");
	for(i = 0; i < n; i++)
	{
	printf("%d  ", arr1[i]);
    } 
	printf("\n");
  printf("The maximum sum from the array such that no two elements are adjacent is: %d \n", maxSumSubseq(arr1, n));
  return 0;
}