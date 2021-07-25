#include <stdio.h>

struct myNum {
    int n1;
    int n2;
    int n3;
} ;

int main() {
  struct myNum num ;
 
  FILE *fp ; // declare a file pointer 
  fp = fopen("myNum.dat","r") ;
  if (fp != NULL) {
      fread(&num, sizeof(struct myNum), 1, fp) ;
      printf("values read by flie are : %d %d %d\n", num.n1, num.n2, num.n3);
  } else {
    printf("Unable to open the file \n");
  }  
  fclose(fp) ;
}
