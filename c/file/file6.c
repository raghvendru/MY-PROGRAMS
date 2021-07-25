#include <stdio.h>


int main() {
  char str[128] ;
  char dst[1280] ;
  char line[128];
  int cnt = 0;
  printf("Enter the name of the file to read from  : \n") ;
  scanf("%s", str);
  printf("Enter the name of the file to write to  : \n") ;
  scanf("%s", dst);
  FILE *fp ; // declare a file pointer 
  FILE *fpw ; // declare a file pointer to write
  fp = fopen(str,"r") ;
  fpw = fopen(dst,"w") ; // file to write to 
  if (fp != NULL && fpw != NULL) {
    printf("Opened file named %s \n", str);
    while (!feof(fp)) {
        fscanf(fp, "%[^\n]\n",line) ;  // read the line
        fprintf(fpw,"%s\n",line) ; // write the line
        printf("%s\n",line) ;
        cnt++;
    }
    printf("looped %d times", cnt);
  } else {
    printf("Unable to open the file %s \n", str);
  }

  // now the file is opened lets read all the data and print it
  fclose(fp) ;
  fclose(fpw) ;
}
