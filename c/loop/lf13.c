/* 
    Loop Problems
    By ITfyMe.com 
*/

// include library
#include <stdio.h>

// swap first and last digits of a number

int main(int argc, char *argv[]) {
    int n ; // for input 
    int result=0; // this is for swapped number
    printf("swap first and last digits of a number\n") ;
    printf("Enter the number: \n");
    scanf("%d", &n) ;
    

    // number system
    // 1234
    // 1*1000 + 2*100 + 3*10 + 4
    // 4231
    // 4*1000 + 2*100 + 3*10 + 1


    int firstDigit = -1 ;   // idenfify the first digit
    int multiplier = 1 ; // multiplier to multiply the digit to find the postion value
    int digit ; // digit 
    int cnt = 1 ;
    while(n > 10) {
        digit = n % 10 ;
        
        if (firstDigit == -1)
            firstDigit = digit ;
        else
            result = result + digit * multiplier ;
        multiplier = multiplier * 10 ;
        n = n / 10 ;
        printf("loop#: %d first digit: %d digit : %d  multiplier: %d num : %d result: %d \n",cnt, firstDigit, digit, multiplier, n, result) ;
        cnt++;
    }
    

    // now num will have last digit
    result = result + n; // adding to 1st place 
    printf("digit : %d  multiplier: %d num : %d result: %d \n", digit, multiplier, n, result) ;
    result = result + firstDigit * multiplier; // add the last place based on the multiplier
    printf("after digit swap\n");
    printf("%d\n",result) ;
    
}