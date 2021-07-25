#include <stdio.h>


int main() {
  char str[128] ;
  char name[128];
  int num, marks ;
  printf("Enter the name of the file to read from  : \n") ;
  scanf("%s", str);
  FILE *fp ; // declare a file pointer 
  fp = fopen(str,"r") ;
  if (fp != NULL) {
    //   fprintf(fp, "%d %s %d \n", 1, "vikram", 100) ;
    //   fprintf(fp ,"%d %s %d \n", 2, "vijay", 100) ;
    //   fprintf(fp, "%d %s %d \n", 3, "vibha", 100) ;
        fscanf(fp,"%d %s %d \n", &num, name, &marks) ;
        printf("%d %s %d \n", num, name, marks) ;
        fscanf(fp,"%d %s %d \n", &num, name, &marks) ;
        printf("%d %s %d \n", num, name, marks) ;
        fscanf(fp,"%d %s %d \n", &num, name, &marks) ;
        printf("%d %s %d \n", num, name, marks) ;
  } else {
    printf("Unable to open the file %s \n", str);
  }

  // now the file is opened lets read all the data and print it
  fclose(fp) ;
}
