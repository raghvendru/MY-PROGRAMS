#include<stdio.h> 
#include<string.h> 
  
int main() 
{  
    char str[]="raghu"; 
    char des[]="raghu"; 
      
    //using strcmp() 
    int res=strcmp(str,des); 
    if (res==0) 
        printf("Strings are equal"); 
    else 
        printf("Strings are unequal");  
    return 0; 
} 