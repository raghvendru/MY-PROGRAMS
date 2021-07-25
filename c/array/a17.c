#include <stdio.h>
int main()
{
   int array[100], pos, i, n;


   printf("Enter number of elements in array\n");
   scanf("%d", &n);
   printf("Enter %d elements\n");
   for (i = 0; i < n; i++)
   scanf("%d", &array[i]);

//ask user to enter
   printf("Enter the locationto delete element\n");
   scanf("%d", &pos);

//impossible condition
   if (pos >= n+1){
   printf("Delete is not possible.\n");}

//deleting element(for ex third position)
//insert fourth with third
   else{
   for (i = pos- 1; i < n - 1; i++){
   array[i] = array[i+1];
    printf("Resultant array:\n");
    }


//then print resultant array
   for (i= 0; i < n - 1; i++){
   printf("%d\n", array[i]);
    }
   }

   return 0;
}