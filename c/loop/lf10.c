#include <stdio.h>

int main(int argc,char*argv[])
{
    int n;
    int count=0;
    printf("Enter an integer: ");
    scanf("%d", &n);
    
    while ( n!=0) {
        n=n%10;
     count++;
    }
    printf("%d",count);
    return 0;
}