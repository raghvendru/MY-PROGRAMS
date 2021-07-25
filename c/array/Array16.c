/* This function swaps two sub arrays of length L, 
starting from left and right index */
void swapArraySegment(int *array, int left, int right, int L){
   int i, temp;
   for(i = 0; i < L; i++) {
   /* swapping array element at index left+i and right+i */
     temp = array[left + i];
     array[left + i] = array[right + i];
     array[right + i] = temp;
   }
}
 
void rotateArray(int *array, int N, int size) { 
  /* Input Validation */
  if(array == NULL || size == 0 || N == 0 || N == size)
    return;
      
  /* If elements to be rotated is equal to 
  first half of the given array  */
  if(size - N == N){
  /* swap left and right half of array */
    swapArraySegment(array, 0, size-N, N);   
    return;
  }  
      
 /* If X(left Segment) is smaller than Y */            
  if(N < size-N) {
  /* Swap X and Y_right */
    swapArraySegment(array, 0, size-N, N);
    /* Recursively swap remaining elements */
    rotateArray(array, N, size-N);    
  } else {
 /* If Y(right Segment) is smaller than X */
    swapArraySegment(array, 0, N, size-N);
    rotateArray(array+size-N, 2*N-size, N);
  }
}
 
int main(){
    int array[10] = {0,1,2,3,4,5,6,7,8,9}; 
    int i;
  
    printf("Original Array\n");
    for(i = 0; i<10; i++){
        printf("%d ", array[i]);
    } 
    rotateArray(array, 4, 10);
  
    printf("\nRotated Array\n");
    for(i = 0; i<10; i++){
        printf("%d ", array[i]);
    }
 
    return 0;
}