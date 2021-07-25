#include <stdio.h>

int main(int argc,char*argv[])
{
    int a= 3;
    int b=1;
    int s;
    s = a-(~b+1);
    printf("value is %d \n",s);
}