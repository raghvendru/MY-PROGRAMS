#include <stdio.h>
int max(int* num1, int* num2)
{
    return (*num1>*num2 ) ? *num1 : *num2;
}


int max(int* num1,int* num2);
int main() 
{
    int num1,num2,maximum;
    int *p,*q;
	p=&num1;
	q=&num2;
    printf("Enter any two numbers");
    scanf("%d%d",&num1,&num2);
    maximum=max(&num1,&num2); 
    printf("\nMaximum=%d\n",maximum);
    return 0;
}


