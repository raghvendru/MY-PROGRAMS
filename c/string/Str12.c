#include <stdio.h>
#include<string.h>
 
void main()
{
    char s[10];
    int freq[26] = {0};
    printf("Enter the string");
    scanf("%s",s);
 
    for(int i=0;i<strlen(s);i++)
    {
        printf("freq[%c-'a']  %d\n", s[i] ,freq[s[i] - 'a']++);
    }
 
    for(int i=0;i<26;i++)
    {
        if (freq[s[i]-'a']==1)
        {
            printf("The first non-repeating character in the string is %c at index %d",s[i],i + 1);
            break;
        }
    }
}