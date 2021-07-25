#include <stdio.h>
int main()
{
    int count=0;
    while(count <= 255)
    {
        printf("ascii value of %C is %d\n",count,count);
        count++;
    }
}