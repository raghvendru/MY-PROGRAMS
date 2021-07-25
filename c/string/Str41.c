#include <stdio.h>
 
// Function to remove all occurrences of `B` and `AC` from the string
void Remove(char* str)
{
    int i=0,k=0;
 
    // do till the end of the string is reached
    while(str[i])
    {
        if(str[i]=='C' && (k>0 && str[k-1]=='A')) {
            --k,++i;
        }
        else if(str[i]=='B') {
            ++i;
        }
 
        else {
            str[k++]=str[i++];
        }
    }
    str[k]='\0';
}
 
int main()
{
    char str[]="ABCACBCAABB";
 
    Remove(str);
    printf("The string after removal removal of 'B' and 'AC' is '%s'",str);
    return 0;
}


