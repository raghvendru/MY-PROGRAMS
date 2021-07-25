#include <stdio.h>

int main(int argc,char*argv[])
{
    char test="raghu";
    printf('%s',test);
    test[6]='\0';
    printf("%s",test)
}