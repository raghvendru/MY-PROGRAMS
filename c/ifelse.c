#include <stdio.h>

int main(int argc,char*argv[])
{
int m=90;
if(m>0 && m<46) {
    printf("failing %d \n",m);
     } else if(m>55 && m<65) {
printf("unsatisfactory %d \n",m);
     } else if(m>66 && m<100) {
printf("satisfactory %d \n",m);
  }


}