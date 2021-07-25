/*
 * C Program to Find the Sum of Contiguous Subarray within a 
 * 1 - D Array of Numbers which has the Largest Sum
 */
 
#include<stdio.h>
 
int main()
{
    int size,m=0,l=0;
 
    printf("Type the length of the array\n");
    scanf("%d",&size);
    int array[size];
    printf("type the elements of the array\n");
 
    for(int i=0;i<size;i++)
    {
        scanf("%d",&array[i]);
 
    }
 
    int largest=array[0];
    for(int i=0;i<size;i++)
    {
        int sum=0;
        for(int j=i;j<size;j++)
        {
            sum=sum+array[j];
            if(sum>largest)
            {
                m=i;l=j;
                largest=sum;
            }
        }
    }
 
    printf("\n The largest contigous subarray is");
    for(int z=m;z<=l;z++)
    {
        printf(" %d ",array[z]);
    }
    printf("\n The sum of the largest contigous subarray is");
    printf(" %d",largest);
    return 0;
}
/*
Program Explanation
1. Take the size of the array as input from users.
2. Then, Initialize an array of size given by the user.
3. Using for loop, take array element as input from users and insert them into the array.
4. After inserting all the elements of the array, consider the very first element of array to be the largest.
5. Run a for loop, from 1 to arraySize-1, extracting array element one by one.
6. Run another loop inside this loop and sum every possible contiguous subarray.
6. If the largest element is smaller than the sum of the current contiguous subarray, then the largest element is updated to the current sum.
7. In the end, the largest element will hold the actual largest sum.

Runtime Test Cases
Here is the runtime output of the C program where the user is reading an array of 8 elements with values as -1,-5,5,3,-2,5,4 and 1 and then it displays the largest contigous subarray with its sum.

Type the length of the array
8
type the elements of the array
-1
-5
5
3
-2
5
4
1
 
The largest contiguous subarray is 5  3  -2  5  4  1
The sum of the largest contiguous subarray is 16
*/