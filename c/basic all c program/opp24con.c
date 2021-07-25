#include <stdio.h>
int main(int argc,char*argv[])


 {
     
   char name[100];
   int age;
   printf("whats your name");
   scanf("%s",&name);
   printf("whats your age");
   scanf("%d",&age);
   printf("your name is %s and you are  %d years old \n",name,age);
}