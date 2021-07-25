#include <stdio.h>
#include<string.h>
int main()  
{  
    char str[] = "raghavendra";  
    int i,counter,len=0,n=3,temp=0,chars;  
    len =strlen(str);  
    chars=len/n;  
    char c[chars+1];  

    //Check divide possibility
    if(len%n!=0 || n==0) {  
        printf("cannot be devide",n);  
    } 

    else {  
            printf("%d equal parts of given string are\n",n);  
            for(i=0;i<len;i=i+chars) {  
                counter=0;   
                while(counter<chars) {  
                    c[counter]=str[i+counter];  
                    counter++;  
                }  
                c[counter]='\0';  
                printf("%s",c);  
                printf("\n");  
            }  
        }  
    return 0;  
}  