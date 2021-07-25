#include <stdio.h>
void reverseSentence();
int main() {
    printf("Enter a sentence: ");
    reverseSentence();
    return 0;
}

void reverseSentence() {
    char c;
    scanf("%c", &c);
    if (c != '\n') {
        reverseSentence();
        printf("%c", c);
    }
}

/*
This program first prints Enter a sentence: . Then, the reverseSentence() function is called.

This function stores the first letter entered by the user in c. If the variable is any character other than \n (newline), reverseSentence() is called again.

This process goes on until the user hits enter.

When the user hits enter, the reverseSentence() function starts printing characters from last.

*/