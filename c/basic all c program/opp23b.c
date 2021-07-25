#include <stdio.h>
#include <string.h>

int main() {
    char a[100];
    printf("enter the string \n");
    scanf("%s",&a);
    printf("after converting string is %s",strupr(a));
    return 1;
}