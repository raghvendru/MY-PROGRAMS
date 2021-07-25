#include<stdio.h>
#include<string.h>
 
int main() {
 
    char inputString[100], leftHalf[100], rightHalf[100];
    int length, mid, i, k;
  
    /* Read input string from user using gets */
    printf("Enter a string\n");
    gets(inputString);
    /* Find length of string using strlen function */
    length = strlen(inputString);
  
    mid = length/2;
    /* Copy left half of inputString to leftHalf */
    for(i = 0; i < mid; i++) {
        leftHalf[i]= inputString[i];
    }
    leftHalf[i] = '\0';
  
    /* Copy right half of inputString to rightHalf  */
    for(i = mid, k = 0; i <= length; i++, k++) {
 rightHalf[k]= inputString[i];
    }
  
    /* Printing left and right half of string */
    printf("Left half : %s\n",leftHalf);
    printf("Right half : %s\n",rightHalf);
 
    return 0;
}

/*
Given a string of length L, we have to split this string into two equal sub-strings.

If L is even, then length of the sub-strings will be L/2 and L/2.
If L is off, the length of sub-strings will be L/2 and (L/2)+1

For Example:
Input : "Internet" 
Output : "Inte" and "rnet"

//Algorithm for split a string in two equal sub strings//
Let inputString be the string entered by user and leftHalf and rightHalf are two output sub-strings.
Find length of the string using strlen function. Let it be L.
Find the mid index of input string. (mid = L/2)
Copy characters of inputString from index 0 to mid to leftHalf.
Copy characters of inputString from index mid+1 to L-1 to rightHalf.
C program to split a string into two equal strings
//
In this program, we will first read a string as input from user using gets function. Then we find the length of input string(L) using strlen function of string.h header file. Now, we will create two sub-strings as explained above and print it on screen.
*/