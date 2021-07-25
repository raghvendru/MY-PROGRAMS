#include <stdio.h>  
#include <string.h>  
   
int main()  
{  
    char string[]="character";  
          
    //displays individual characters from given string  
    printf("individual character\n");  
      
    //iterate through the string and display individual character  
    for(int i=0;i<strlen(string);i++){  
        printf("%c",string[i]);  
    }  
          
    return 0;  
}  
