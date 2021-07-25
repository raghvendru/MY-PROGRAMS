#include <stdio.h>
#include <string.h>

void reverse(char str[], int start, int end) {
    char temp ;
    if (end == start) {
        return ;
    }
    temp = str[end];
    str[end] = str[start];
    str[start] = temp;
    
    if (end - start == 1) {
     return ;     
    } 
    
    // swap the rest
    reverse(str, start+1, end-1);
}


int main() {
    char str1[100] ;
    printf("Enter a string to reverse \n");
    scanf("%s", &str1);
    printf("String is %s \n", str1);
    reverse(str1, 0, strlen(str1)-1);
    printf("String is %s \n", str1);
    return 0;
}