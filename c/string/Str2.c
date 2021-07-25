#include<stdio.h>
#include<string.h>
 
int main()
{
    char s[1000];  
    int i,n,c=0;
 
    printf("enter  the string");
    gets(s);
    n=strlen(s);
 
    for(i=0;i<n/2;i++)  
    {
    	if(s[i]==s[n-i-1])
    	c++;
 
 	}
	printf("%d\n",c);
 	if(c==i)
 	    printf("string is palindrome\n");
    else
        printf("string is not palindrome\n");
 
 	 return 0;
}
