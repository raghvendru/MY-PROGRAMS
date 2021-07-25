#include <stdio.h>

void printArray(int a[], int size) {
    for (int i =0; i<size;i++) {
        printf("%d ", a[i]);

     }
    printf("\n");
} 
 

void insertionsort(int a[], int size) {
    for (int i = 1; i<size;i++) {
        int key = a[i];
        int j = i - 1 ;
        while (j>=0 && a[j]>key) {
            a[j+1] = a[j] ;
            j = j -1;
        }
        a[j+1] = key ;
    }
}
     
     
int main() {
    int a[10] = {1,5,2,67,-9,38,376,8,3,456} ;
    printArray(a,10) ;
    insertionsort(a,10);
    printArray(a,10);
}