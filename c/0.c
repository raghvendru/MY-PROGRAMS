/* 
    Array Problems
    By ITfyMe.com 
*/

#include <stdio.h>  
#include <stdlib.h>
#include <limits.h>
  
int findMin(int a[],int n) {
    int min = INT_MAX;
    printf("INT_MAX is %d \n", min) ;
    for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
        printf("%d %d \n", a[i], min);
        if (a[i] < min) {
            min = a[i] ;
        }
    }
    printf("reurning %d\n", min);
    return min ;
}

int findMax(int a[],int n) {
    int max = INT_MIN;
    printf("INT_MIN is %d \n", max) ;
    for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
        // printf("%d %d \n", a[i], min);
        if (a[i] > max) {
            max = a[i] ;
        }
    }
    printf("reurning %d\n", max);
    return max ;
}

int getRandomNumberFrom(int max) { // gives me random number between 0 to max 
    int rnd = rand() ;
    // printf("random number is %d \n", rnd) ;
    return rnd % max ;
}

int  main() {  
    int arr[100]; 
    int n ; // read the number of items ... for the time being lets limit this number to 20
    int sum = 0;
    scanf("%d",&n) ; // accept n 
    srand(time(0));
    // initializing
    for(int i=0; i<n; i++) {  
        arr[i]= getRandomNumberFrom(100) ; // here i want to get only numbers from 1 to 100 
        
    }  
  
    for(int i=0; i<n; i++) {  
        printf("%d ", arr[i]);
    } 
    printf("\n");
    printf("minimum is %d and max is %d \n", findMin(arr, n),findMax(arr, n)) ;
    printf("\n");	
    return 0;
}