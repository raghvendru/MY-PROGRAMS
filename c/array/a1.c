#include <stdio.h>  
  
int  main() {  
     int i, n;
    int arr[100];
    printf("Enter the number of elements (1 to 100): ");
    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        printf("Enter number%d: ", i + 1);
        scanf("%d", &arr[i]);}
  
    printf("\nElements in array in order are: ");  
    for(int i=0; i<=n-1; i++) {  
        printf("%d  ", arr[i]); 
         
    } 
    printf("\n");	
    return 0;
}