#include <stdio.h>
 
int findOddCountElem (int *arr1, int n ) 
{
     int i, ResultXor = 0; 
     for(i = 0; i < n; i++)
	 {
      ResultXor = ResultXor ^ arr1[i];
	  }
     return ResultXor;
}
 
int main()
{
    int i;
 int arr1[] = {8, 3, 8, 5, 4, 3, 4, 3, 5};
 
    int ctr = 9;
    printf("The given array is :  ");

	for(i = 0; i < ctr; i++)
	{
	printf("%d  ", arr1[i]);
    } 
    printf("\n");
 
 printf("Number of odd number occur(s) : %d times.\n", findOddCountElem(arr1, ctr));
 return 0;
}