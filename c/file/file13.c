#include <stdio.h>
#include <stdlib.h>

int main() {
  int num[10] ;
  FILE *fp ; // declare a file pointer 
  fp = fopen("rand.dat","r") ;
  if (fp != NULL) {
      fseek(fp, sizeof(int)*40001, SEEK_SET);
      fread(&num, sizeof(int),10,fp) ;
      for (int i =0;i<10;i++) {
          printf("value of %d is %d \n", 40001+i, num[i]) ;
      }
  } else {
    printf("Unable to create the file \n");
  }  
  fclose(fp) ;
}
