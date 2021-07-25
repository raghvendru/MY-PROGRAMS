#include <stdio.h>
#include <string.h>

int isPalindrome(char str[], int start, int end) {
    if (end - start == 1) {
        return str[start] == str[end] ;
    } 
    if (end == start) {
        return 1;
    }
    int same = (str[start] == str[end]) && isPalindrome(str, start+1, end -1 );
    return same;
}


int main() {
    char str1[100] ;
    printf("Enter a string to check \n");
    scanf("%s", &str1);
    int isPal = isPalindrome(str1, 0, strlen(str1)-1);
    printf("%s is %s\n", str1, isPal == 1 ? "Palindrome" : "not a palindrome");   
    return 1;
}