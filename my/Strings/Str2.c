#include<stdio.h>
#include<string.h>
 
int main()
{
    char s[1000];  
    int i,n,c=0;
 
    printf("Enter  the string : ");
    gets(s);
    n=strlen(s);
 
    for(i=0;i<n/2;i++)  
    {
    	if(s[i]==s[n-i-1])
    	c++;
 
 	}
	printf("%d\n", c);
 	if(c==i)
 	    printf("string is palindrome");
    else
        printf("string is not palindrome");
 
 	 
     
    return 0;
}
/*
1)If the original string is equal to reverse of that string, then the string is said to be a palindrome.
2)Read the entered string using gets(s).
3) Calculate the string length using string library function strlen(s) and store the length into the variable n.
4) i=0,c=0.

Compare the element at s[i] with the element at s[n-i-1].If both are equal then increase the c value.

Compare the remaining characters by increasing i value up to i<n/2.


 
5) If the number of characters compared is equal to the number of characters matched then the given string is the palindrome.

*/