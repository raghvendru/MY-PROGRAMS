/*
 * C Program to Count Number of Words in a given Text Or Sentence
 */
#include <stdio.h>
#include <string.h>
 
void main()
{
    char s[200];
    int count = 0, i;
 
    printf("Enter the string:\n");
    scanf("%[^\n]s", s);
    for (i = 0;s[i] != '\0';i++)
    {
        if (s[i] == ' ' && s[i+1] != ' ')
            count++;    
    }
    printf("Number of words in given string are: %d\n", count + 1);
}

/*
Problem Description
This program takes a string as input and count the number of words in the input string.

Problem Solution
1. Take a string as input.
2. Using for loop search for a empty space in between the words in the string.
3. Consecutively increment a variable. This variable gives the count of number of words.

Program Explanation
1. Take a string as input and store it in the array s[].
2. Using for loop search for a space ‘ ‘ in the string and consecutively increment a variable count.
3. Do step-2 until the end of the string.
4. Increment the variable count by 1 and then print the variable count as output.
*/