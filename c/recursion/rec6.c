#include<stdio.h>
void word(int n)
{
    if(n==0)      //base
    return;
    word(n/10);
    switch(n%10)   //comp and decomp
    {

        case 0:
        printf("zero ");
        break;
        case 1:
        printf("one ");
        break;
        case 2:
        printf("two ");
        break;
        case 3:
        printf("three ");
        break;
        case 4:
        printf("four ");
        break;
        case 5: 
        printf("five");
        break;
        case 6: 
        printf("six");
        break;
        case 7: 
        printf("seven");
        break;
        case 8: 
        printf("eight");
        break;
        case 9:
        printf("nine");
        break;
    }

}

void word(int n);
int main()
{
    int num ;
    printf("Enter any number");
    scanf("%d",&num);
    word(num);
    return 0;
 
}