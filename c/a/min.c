#include <stdio.h>
#include <stdlib.h>
#include <limits.h>  
int  main() {  
    int arr[10] = {38,483,54,29,8,76,-98,78,778,-98}; 
    int n ; 
    int sum = 0;
    int findMin(int a[],int n) {
    int min = INT_MAX ;

    for (int i =0; i<n;i++) {
        if (min > a[i])
            min = a[i];
    }
      printf("reurning %d\n", max);
    return min ;
}
int findMax(int a[],int n) {
    int max = INT_MIN;
    printf("INT_MIN is %d \n", max) ;
    for (int i = 0; i < n; i++) { 
        if (a[i] > max) {
            max = a[i] ;
        }
    }
    printf("reurning %d\n", max);
    return max ;
}

}