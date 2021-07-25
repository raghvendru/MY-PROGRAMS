#include <stdio.h>
#include <stdlib.h>

int countTriangles(int arr[],int n){
   // Count of triangles
   int count=0;
   for (int i=0;i<n-2;i++){
      for (int j=i+1;j<n-1;j++){
         for (int k=j+1;k<n;k++){
            //any two sides have sum > third
            if ( arr[i]+arr[j]>arr[k] && arr[i]+arr[k]>arr[j] && arr[k]+arr[j]>arr[i])
              { count++; }
         }
      }
   }
   return count;
}
int main(){
   int Arr[]={1,2,5,3,6,8,10};
   int len=7;
   printf("count of Triangles possible %d\n", countTriangles(Arr,len));
   return 0;
}