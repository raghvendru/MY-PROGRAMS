#include <stdio.h>

int main() {
  char str[128] , dst[128] ;
  int cnt = 0;
  printf("Enter the name of the file to read from  : \n") ;
  scanf("%s", str);
  printf("Enter the name of the file to write from  : \n") ;
  scanf("%s", dst);
  FILE *fp ; // declare a file pointer for read
  FILE *fpw ; // declare a file pointer for write
  fp = fopen(str,"r") ;
  fpw = fopen(dst, "w");
  // now the file is opened lets read all the data and print it
  if (fp != NULL && fpw != NULL) {
    printf("Opened file named %s \n", str);
    while (!feof(fp)) {
        int c = fgetc(fp) ;
        printf("%c", c) ;
        fputc(c,fpw) ;
        cnt++;
    }
    printf("looped %d times", cnt);
  } else {
    printf("Unable to open the file %s \n", str);
  }
  fclose(fp) ;
  fclose(fpw);
}
