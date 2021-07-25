#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int main()
{
    int val;
    char strn1[] = "12546";
 
    val = atoi(strn1);
    printf("String value = %s\n", strn1);
    printf("Integer value = %d\n", val);
 
    char strn2[] = "GeeksforGeeks";
    val = atoi(strn2);
    printf("String value = %s\n", strn2);
    printf("Integer value = %d\n", val);
 
    return (0);
}

/*
The atoi() function in C takes a string (which represents an integer) as an argument and returns its value of type int. So basically the function is used to convert a string argument to an integer.

Syntax:  

int atoi(const char strn)
Parameters: The function accepts one parameter strn which refers to the string argument that is needed to be converted into its integer equivalent.
Return Value: If strn is a valid input, then the function returns the equivalent integer number for the passed string number. If no valid conversion takes place, then the function returns zero.

*/