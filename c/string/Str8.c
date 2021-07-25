#include <stdio.h>  
#include <string.h>  
   
int main()  
{  
    char str1[]="abcde",str2[]="deabc";  
if(strlen(str1)!=strlen(str2)){  
        printf("second string is not a rotation of first");  
    }  
    else{  
        //concatenate str1 with str1 and store it in str1  
        strcat(str1, str1);  
          
        //check whether str2 is present in str1  
        if(strstr(str1,str2)!=NULL)  
            printf("Second string is a rotation of first string");  
        else  
            printf("Second string is not a rotation of first string");  
    }  
   
    return 0;  
}  