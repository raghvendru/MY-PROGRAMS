#include <stdio.h>  
#include <string.h>  
   
int main()  
{  
    char string[] = "characters";  
          
    //Displays individual characters from given string  
    printf("Individual characters from given string:\n");  
      
    //Iterate through the string and display individual character  
    for(int i = 0; i < strlen(string); i++){  
        printf("%c ", string[i]);  
    }  
          
    return 0;  
}  
/*
Algorithm
Define a string.
Define a for-loop in which loop variable will start from 0 and end at length of the string.
To separate each character, we will print the character present at every index.
To visualize this, we have separated each character by a space.
*/