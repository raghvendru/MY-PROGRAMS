#include <stdio.h>  
#include <stdlib.h>
#include <limits.h>
  int main()
  {
      int src[5];
      int dest[5];
      for(int i=0;i<5;i++){
          src[i]=rand();
          printf("%d\n",src[i]);
      }
        printf("dest elem are \n");
        for(int i=0;i<5;i++)
        {
            dest[i]=src[i];
             printf("%d\n",dest[i]);
        }
      return 0;
      
  }