#include <stdio.h>

struct myNum {
    int n1;
    int n2;
    int n3;
} ;

int main() {
  struct myNum num ;
  num.n1 = 10 ; num.n2 = 100; num.n3 = 1000 ;

  FILE *fp ; // declare a file pointer 
  fp = fopen("myNum.dat","w") ;
  if (fp != NULL) {
      fwrite(&num, sizeof(struct myNum), 1, fp) ;
  } else {
    printf("Unable to create the file \n");
  }  
  fclose(fp) ;
}
