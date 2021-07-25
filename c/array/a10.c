#include <stdio.h>
int main()
{
    int arr1[100], n,cou=0;
    int i, j;
       printf("Enter the number of elements (1 to 100): ");
    scanf("%d", &n);
     for (i = 0; i < n; ++i) {
        printf("Enter number%d: ", i + 1);
        scanf("%d", &arr1[i]);}
    printf("\nThe unique elements found in the array are: \n");
    for(i=0; i<n; i++){
        cou=0;
        for(j=i+1; j<n; j++)
        {
            if (i!=j){
		       if(arr1[i]==arr1[j]){
                 cou++;
               }
             }
        }
       if(cou==0){
          printf("%d ",arr1[i]);
        }
    }
       printf("\n");
}