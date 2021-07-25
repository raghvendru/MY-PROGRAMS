#include <stdio.h>


int main() {
  char str[128] ;
  char line[128];
  int cnt = 0;
  printf("Enter the name of the file to read from  : \n") ;
  scanf("%s", str);
  FILE *fp ; // declare a file pointer 
  fp = fopen(str,"r") ;
  if (fp != NULL) {
    printf("Opened file named %s \n", str);
    while (!feof(fp)) {
        fgets(line, 128, fp) ;
        printf("%s",line) ;
        cnt++;
    }
    printf("looped %d times \n",cnt) ;
  } else {
    printf("Unable to open the file %s \n", str);
  }

  // now the file is opened lets read all the data and print it
  
  fclose(fp) ;
}
