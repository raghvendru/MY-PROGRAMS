#include <stdio.h>
#include <stdbool.h>

int main(int argc,char*argv[])
{
    char a='c';
    char str[]="uuu";
    bool b=0;
    printf("address is %p,size is %c and value is %d \n",&a,sizeof(char),a);
    printf("address is %p,size is %s and value is %d \n",str,sizeof(str),&a);
    printf("address is %p,size is %b and value is %d \n",b,sizeof(b),&a);
}
