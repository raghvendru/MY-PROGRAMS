#include<stdio.h> 
int maxDiff(int arr[],int size) 
{      
  int max_diff =arr[1]-arr[0];  //initially assume o and 1th elem is max diff
  int i,j; 
  for(i=0;i<size;i++) 
  { 
    for(j=i+1;j<size;j++) 
    {         
      if(arr[j]-arr[i]>max_diff)    
         max_diff=arr[j]-arr[i]; 
    }     
  }           
  return max_diff; 
}     
  
int main() 
{ 
  int arr[]={1,2,90,10,110}; 
  printf("Maximum difference is %d",maxDiff(arr,5)); 
  getchar(); 
  return 0; 
} 