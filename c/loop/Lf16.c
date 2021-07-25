// Write a C program to reverse a number using for loop
#include <stdio.h>
int main()
{
    int n, reverse_Number = 0, rem,Original_number=0;
 
    printf("Enter a number to get reverse number ");
    scanf("%d", &n);
    Original_number=n;
    while(n != 0)
    {
        rem = n%10;
        reverse_Number = reverse_Number*10 + rem;
        n /= 10;
    }
 
    printf("Reversed Number of %d is = %d",Original_number,reverse_Number);
 
    
}