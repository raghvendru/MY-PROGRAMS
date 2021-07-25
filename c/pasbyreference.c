#include<stdio.h>
int square (int*a)
{
*a=(*a) * (*a);

return *a;
}
int main(int argc,char*argv[]) {
    int a=10;
    int b=square(&a);
    printf("%d,%d\n",a,b);
    

    return 1;
}
