#include<stdio.h>
#include<conio.h>
void main()
{
    int n ; // 
    int a[10] ;
    int l1=2000;//smallest(assuming both are having max value)
    int l2=2000;//second smallest
    int i;
    
    
    printf("enter size");
    scanf("%d",&n);
    printf("enter array elements");
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
//finding second largest
for(i=0;i<n;i++){

   if(a[i]<l1){
        l2=l1;
       l1=a[i];
    }
   else if(a[i]<l2 && a[i]>l1){
       l2=a[i];
   }
}
printf("second smallest is=%d",l2);
}