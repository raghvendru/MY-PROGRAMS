#include <stdio.h>

void printArray(int a[], int size) {
    for (int i =0; i<size;i++) {
        printf("%d ", a[i]);
    }
    printf("\n");
}

void simpleSort(int a[], int size) {
    for (int i = 0; i<size;i++) {
        int min_idx = i;
        for (int j = i+1; j < size;j++) {
            if (a[j] < a[min_idx])     
                min_idx = j;
        }
        // swap min_idx and i element
        int temp = a[min_idx];
        a[min_idx] = a[i];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              a[i] = temp;
        
    }
}

int main() {
    int a[10] = {1,5,2,67,-9,38,376,8,3,456} ;
    printArray(a,10) ;
    simpleSort(a,10);
    printArray(a,10);
}