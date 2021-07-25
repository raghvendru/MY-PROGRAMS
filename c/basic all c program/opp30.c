#include <stdio.h>

int main(int argc,char*argv[])
{
    int a= 3,b=5;
    int s;
    s = a+(~b+1);
    printf("value is %d \n",s);
}