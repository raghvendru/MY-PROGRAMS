#include<stdio.h>
#include<string.h>
int main(){
   char str1[]="abc";
   char str2[]="abc";
   int count=0;
   int cmp=0;
   while(str1[count]!='\0' ||  str2[count]!='\0')
   {
       if(str1[count]!=str2[count])
       {
           cmp=1;
           break;
       }
       count++;
   }
   if(cmp==0)
   printf("String %s and %s are equal",str1,str2);
   else
   printf("Strings %s and %s are not equal",str1,str2);
}