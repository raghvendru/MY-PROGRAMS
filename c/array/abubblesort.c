#include <stdio.h>

void printArray(int a[], int size) {
    for (int i =0; i<size;i++) {
        printf("%d ", a[i]);
    }
    printf("\n");
}

void swap(int a[], int i, int j) {
    int temp = a[i] ;
    a[i] = a[j];
    a[j] = temp ;
}
void bubbleSort(int a[], int size) {
    for (int i = 0; i<size;i++) {
        for (int j = size-1 ; j > i;j--) {
            if (a[j] < a[j-1])
                swap(a, j, j-1) ;
        }
        printf("after %d iteration the array is \n",i+1);
        printArray(a,size);
    }
}

int main() {
    
    int a[10] = {1,5,2,67,-9,38,376,8,3,456} ;
    printArray(a,10) ;
    bubbleSort(a,10);
    printArray(a,10);
}
