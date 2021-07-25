#include <stdio.h>

int main()
{
    int n ; // 
    int a[10] ;
    int pos ;
    int ele ;
    int i;
    
    // 
    printf("enter size");
    scanf("%d",&n);
    printf("enter array elements");
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }

    //
    printf("enter pos to be inserted");
    scanf("%d",&pos);
    printf("enter elements");
    scanf("%d",&ele);

    // inserting at position pos (say for example 3 with n as 5)
    // now shift items in 3,4,5 to 4,5,6
    for(i=n-1;i>=pos-1;i--){
        a[i+1]=a[i];
    }




    // now insert at pos
    a[pos-1]=ele;
    printf("aftrer insertion\n");
    for(i=0;i<=n;i++)
        printf("%d\n",a[i]);
}