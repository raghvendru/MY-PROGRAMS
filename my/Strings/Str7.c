/*
 * C Program to Remove all Characters in Second String which are 
 * present in First String 
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
 
int main ()
{
    char str1[100], str2[100], str_rem[100];
    int i = 0, j = 0, k = 0;
 
    printf ("Enter the First string:\n");
    gets (str1);
 
    printf ("Enter the Second string:\n");
    gets (str2);
 
    for (i = 0; str1[i]!= '\0'; i++)
    {
        for (j = 0; str2[j] != '\0'; j++)
        {
            if (str1[i] == str2[j])
            {
                continue;
            }
            else
            {
                str_rem[k] = str2[j];
                k ++;
            }
        }
        str_rem[k] = '\0';
        strcpy (str2, str_rem);
        k = 0;
    }
 
    printf ("On removing characters from second string we get: %s\n", str_rem);
 
    return 0;
}

/*
Program Explanation
1. In this C program, we are reading two string values using the ‘str1’ and ‘str2’ variables respectively. str1 is used to store the first string and str2 is used to store the second string. str_rem is used to store the remaining string after the character removal.
2. Use fflush() function to get the input from the user. Before getting the input make sure you flush the stdin buffer out.
3. for loop statement is used to check that the match is found or not. If the match is not found, traverse the strings and copy the corresponding characters from str2 to str_rem. If the match is found, just skip copying.
4. Append the \0 null character to the end of str_rem and print the characters removed in the second string which are present in the first string.
*/