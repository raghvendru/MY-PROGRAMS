#include <stdio.h>  
  
int  main() {  
     int i, n;
    int arr[100];
    int sum=0;
    printf("Enter the number of elements (1 to 100): ");
    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        printf("Enter number%d: ", i + 1);
        scanf("%d", &arr[i]);}
  
    printf("\nsum of Elements in array in  are: ");  
    for(int i=0; i<=n-1; i++) {  
        sum=sum+arr[i];
        
    } 
    printf("%d",sum);
    printf("\n");	
    return 0;
}