#include <stdio.h>
#include "itmath.h"
int main(int argc,char*argv[]){
  int x;
  x=isSTRONG(145);
  if(x==1){
      printf("strong");
  }
  else
   printf(" not strong");
}