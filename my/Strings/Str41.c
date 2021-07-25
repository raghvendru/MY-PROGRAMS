#include <stdio.h>
 
// Function to remove all occurrences of `AB` and `C` from the string
void Remove(char* str)
{
    // `i` maintains the position of the current char in the input string.
    // `k` maintains the next free position in the output string.
    int i = 0, k = 0;
 
    // do till the end of the string is reached
    while (str[i])
    {
        // if the current is `B` and previous (need not be adjacent) was `A`,
        // increment `i` and decrement `k`
        if (str[i] == 'B' && (k > 0 && str[k - 1] == 'A')) {
            --k, ++i;
        }
 
        // if the current char is `C`, increment `i`
        else if (str[i] == 'C') {
            ++i;
        }
 
        else {
            // for any other character, increment both `i` and `k`
            str[k++] = str[i++];
        }
    }
 
    // null-terminate the string
    str[k] = '\0';
}
 
int main()
{
    char str[] = "ABCACBCAABB";
 
    Remove(str);
    printf("The string after removal removal of 'AB' and 'C' is '%s'", str);
 
    return 0;
}