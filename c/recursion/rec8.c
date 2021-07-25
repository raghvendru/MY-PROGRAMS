#include <stdio.h>

int addNumbers(int n) {
    if (n != 0)
        return n + addNumbers(n - 1);
    else
        return n;
}

int addNumbers(int n);
int main() {
    int num;
    printf("Enter a number");
    scanf("%d", &num);
    printf("Sum %d= %d", addNumbers(num));
    return 0;
}