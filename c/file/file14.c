#include <stdio.h>
#include <strings.h>

int main() {
  char line[256] ;
  FILE *fp ; // declare a file pointer 
//   fp = fopen("state.csv","r+") ;
  fp = fopen("state.csv","w+") ;
  str
  if (fp != NULL) {
      fseek(fp, 24,1);
    //   fgets(line,256,fp) ;
    //   printf("line read is %s \n", line);
      strcpy(line,"hello, this is new line\n") ;
      printf("fp is now in %lu \n", ftell(fp));
      printf("line is now %s\n", line);
      fputs(line, fp);
  } else {
    printf("Unable to create the file \n");
  }  
  fclose(fp) ;
}
