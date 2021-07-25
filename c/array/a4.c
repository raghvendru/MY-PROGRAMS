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
    for(int i=0; i<n; i++) {  
        sum = sum + arr[i];
    } 
    printf("Avg is %f \n", sum*1.0/n) ;
    printf("\n");	
    return 0;
}