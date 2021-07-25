#include <stdio.h>
int main() {
    int k[5];

    printf("Enter elements");
    for (int i=0;i<5;i++)
        scanf("%d",k+i);

    printf("You entered\n");
    for (int i=0;i<5;i++)
        printf("%d\n",*(k+i));
    return 0;
}