#include <stdio.h>
#include <string.h>

void swap(char *ch1, char *ch2) {
    char tmp;
    tmp = *ch1;
    *ch1 = *ch2;
    *ch2 = tmp;
}

void perm(char *array, int start, int end) {
   int i;
   if (start == end)
     printf("%s \n", array);
   else {
       for (i = start; i <= end; i++) {
          swap((array+start), (array+i));
          perm(array, start+1, end);
          swap((array+start), (array+i)); // swap back to get original string to proceed next 
       }
   }
}
 
int main(int argc, char *argv[]) {
    int n = strlen(argv[1]);
    printf(" The permutations of the string are : \n");
    perm(argv[1], 0, n-1);
    return 0;
}                               


