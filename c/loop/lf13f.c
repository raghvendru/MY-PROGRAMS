#include <stdio.h>
int main(int argc, char *argv[]) {
    int n,res;
    int digit;
    int temp=n;
    int numofd=0;
    int maxmult=0;
    int res=0;
    int multi;
    int i;
    printf("swap first and last digits of a number\n") ;
    printf("Enter the number: \n");
    scanf("%d", &n) ;
    digit=temp%10;
if( i==1) {
    res=digit*maxmult;
}
else if(i==numofd) {
    res=res+digit;
} else {
    res=res+digit*multi;
}
multi=multi*10;
printf("multi %d \n",multi);
temp=temp/10;
}
printf("res %d \n",res);

}
