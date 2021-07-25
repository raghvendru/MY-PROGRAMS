#include <stdio.h>

void findFact(int n,int *f){
    int i;
    *f=1;
    for(i=1;i<=n;i++){
        *f=*f*i;
    }
}    

void findFact(int,int*);
int main()
{
    int fact;
    int num1;
	printf("enter a number");
	scanf("%d",&num1);		 
    findFact(num1,&fact);
    printf("factorial of %d is %d\n",num1,fact);
    return 0;
}

  