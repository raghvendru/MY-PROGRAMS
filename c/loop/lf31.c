#include <stdio.h>
#include <math.h>

int main()
{
    int min,max,n, t,t1, remainder, result = 0, count= 0 ;

    printf("Enter two integer: ");
    scanf("%d %d", &min,&max);
    printf("arm no between %d and %d are",min ,max);

     t = n;
     t1=n;

for(n=min+1;n<max;++n){
    t=n;
    while (n != 0)
    {
        n /= 10;
        ++count;
    }
    t=n;


}

   while (t != 0)
    {
        remainder = t%10;
        result += pow(remainder, count);
        t /= 10;
    }
    t1=n;

    if(result == t1)
        printf("%d", t1);
    

    return 0;
}