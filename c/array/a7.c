/* 
    Array Problems
    By ITfyMe.com 
*/

#include <stdio.h>  
#include <stdlib.h>
#include <limits.h>

  
int findMin(int a[],int n) {
    int min = INT_MAX ;

    for (int i =0; i<n;i++) {
        if (min > a[i])
            min = a[i];
    }
    return min ;
}
    // int a,i;
    //   printf("Enter the number of elements (1 to 100): ");
    // scanf("%d", &n);

    // for (i = 0; i < n; ++i) {
    //     printf("Enter number%d: ", i + 1);
    //     scanf("%d", &a[i]);}
    // int min = INT_MAX;
    // printf("INT_MAX is %d \n", min) ;
    // for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
    //     printf("%d %d \n", a[i], min);
    //     if (a[i] < min) {
    //         min = a[i] ;
    //     }
    // }
    // printf("reurning %d\n", min);
    // return min ;
// }

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

int findSum(int a[],int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
        sum = sum + a[i];
    }
    return sum ;
}

float findAvg(int a[],int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
        sum = sum + a[i];
    }
    return sum*1.0/n ;
}

int findNum(int a[],int n) {
    int found = 0;
    for (int i = 0; i < n; i++) { // this is for loop for getting each item in an array
        if (a[i] == n) {
            found = 1 ;
            break ;
        }
    }
    return found ;
}

int getRandomNumberFrom(int max) { // gives me random number between 0 to max 
    int rnd = rand() ;
    // printf("random number is %d \n", rnd) ;
    return rnd % max ;
}

int  main() {  
    int arr[10] ; 
    int n ; // read the number of items ... for the time being lets limit this number to 20
    int sum = 0;
    // findMin(arr,10) ;
    // scanf("%d",&n) ; // accept n 
    // srand(time(0));
    // initializing
    srand(time(0));
    for(int i=0; i<10; i++) {  
        arr[i]= getRandomNumberFrom(100) ; // here i want to get only numbers from 1 to 100     
    }  
  
    for(int i=0; i<10; i++) {  
        printf("%d ", arr[i]);
    } 
    printf("\n");
    printf("minimum is %d and max is %d \n", findMin(arr, 10),findMax(arr, 10)) ;
    printf("sum is %d and avg is %f \n", findSum(arr, 10),findAvg(arr, 10)) ;
    printf("Enter the number to find in the array\n");	

    scanf("%d",&n) ;
    findNum(arr, n);
    printf("Number %d is %s \n", n, findNum(arr,n) == 1 ? " found" : " not found") ;
    
    return 0;
}