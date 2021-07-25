#include <stdio.h>
#include <string.h>

int main (void) {
   char s1[]="raghu";
   char s2[]="ughar";
   char temp;
   int i,j;
   int n=strlen(s1);
   int n1=strlen(s2);

   if( n!=n1){    //len should be same to compare anagram
   
      printf("%s and %s not anagrams\n",s1,s2);
      return 0;
   }

   for (i=0;i<n-1;i++){    //sorting s1 n s2 so that easy for comparing
      for (j=i+1;j<n;j++){
         if (s1[i]>s1[j]){
            temp=s1[i];
            s1[i]=s1[j];
            s1[j]=temp;
         }
         if (s2[i]>s2[j]){
            temp=s2[i];
            s2[i]=s2[j];
            s2[j]=temp;
         }
      }
   }

   for(i=0;i<n;i++){
      if(s1[i]!=s2[i]){    //not equal case
         printf("%s n %s not anagrams\n",s1,s2);
         return 0;
      }
   }//else its anagram
   printf("%s and %s Strings are anagrams\n",s1,s2);
   return 0;
}
