#include <stdio.h>
#include<string.h>
int main()
{
  char str[]="hi raghu";
  char stoken[1024];
  int i=0,tcnt=0;
  int len=strlen(str);
  char token=' ';

  for(i=0;i<strlen(str);i++)
  {
    if(str[i]==token)
    {
      stoken[tcnt]='\0';
      printf("%s\n",stoken);
      tcnt=0;
    }
    else{
        stoken[tcnt]=str[i];
        tcnt++;
    }
  }
   printf("%s\n",stoken);
 return 0;
} 