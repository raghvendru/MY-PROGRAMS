#include <stdio.h>
#include <stdlib.h>

int main() {
  int num;
  FILE *fp ; // declare a file pointer 
  fp = fopen("rand.dat","w") ;
  if (fp != NULL) {
      for (int i=0;i<100000;i++) {
          num = rand();
          if (i > 40000 && i < 40010) {
              printf("%d value is %d \n",i,num) ;
          }
          fwrite(&num, sizeof(int),1,fp) ;
      }
  } else {
    printf("Unable to create the file \n");
  }  
  fclose(fp) ;
}
