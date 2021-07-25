#include <stdio.h>

int main()
{
    int i,test_cases,arr[100],max[100],j=0;

    scanf("%d",&test_cases);
    for(i=0; i<test_cases; i++)
    {
        scanf("%d",&arr[i]);
        max[i]=0;
    }
    max[j++] = arr[test_cases-1];
    for(i=test_cases-1; i>=0; i=i-1)
        if( arr[i] >= max[j-1] )
        {
            max[j] = arr[i];
            j++;
        }
    for(j=j-1; j>0; j=j-1)
        printf("%d\n",max[j]);
    return 0;
}

/*
You are given an array. You have to write a program that will print all the leaders in the array. An element is leader if it is greater than all the elements to its right side. And the rightmost element is always a leader.
For example array {6, 7, 4, 3, 5, 2}, leaders are 7, 5 and 2.
Sample Input
6
6 7 4 3 5 2
Sample Output
7
5
2
Explanation
6 is number of elements
6 7 4 3 5 2 are the elements of the array
7 5 2 is the output
All the elements to the right of 7 are smaller viz 4 3 5 2
All the elements to the right of 5 are smaller than 5
2 is the rightmost element*/