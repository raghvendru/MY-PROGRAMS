#include<stdio.h>
#include<conio.h>
void main()
{
    int n ; // 
    int a[10] ;
    int l1=0;//largest(assuming both are having min value)
    int l2=0;//second largest
    int i;
    
    
    printf("enter size");
    scanf("%d",&n);
    printf("enter array elements");
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
//finding second largest
for(i=0;i<n;i++){

   if(a[i]>l1){
         l2=l1;
          l1=a[i];
    }
   else if(a[i]>l2 && a[i]<l1){
       l2=a[i];
   }
}
printf("second largest is=%d",l2);
}