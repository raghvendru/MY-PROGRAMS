#include <stdio.h>


int main() {
  char str[128] ;
  int cnt = 0;
  printf("Enter the name of the file to read from  : \n") ;
  scanf("%s", str);
  FILE *fp ; // declare a file pointer 
  fp = fopen(str,"r") ;
  // now the file is opened lets read all the data and print it
  if (fp != NULL) {
    printf("Opened file named %s \n", str);
    while (feof(fp)) {
        printf("%c", fgetc(fp)) ;
        cnt++;
    }
    printf("looped %d times", cnt);
    fclose(fp) ;
  } else {
    printf("Unable to open the file %s \n", str);
  }
}
