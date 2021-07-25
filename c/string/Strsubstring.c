#include <stdio.h>
int main()
{
  char str[]="aass";
  char sub[]="ia";
  int i,j=0,k;
  for(i=0;str[i];i++)
  {
    if(str[i]==sub[j])
    {
      for(k=i,j=0;str[k] && sub[j];j++,k++)
        if(str[k]!=sub[j])
            break; 
       if(sub[j]=='\0'){
        printf("Substring");
        return 0;
      }
    }
  }
  printf("Not a substring");
 return 0;
} 