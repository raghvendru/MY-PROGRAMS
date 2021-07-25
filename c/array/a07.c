#include <stdio.h>  
int  main()
{  
    int i, n, flag =0;
    int arr[100];
    int k;
    printf("Enter the number of elements (1 to 100): ");
    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        printf("Enter number %d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    printf("enter k:");
    scanf("%d",&k);
    for(i=0;i<n;i++){
    if (arr[i]==k){
    flag = 1;
    break;
    }
else
    {
    flag=-1;
    break;
    }
    }
   if(flag == 1)
   printf("no is present: %d",k);
   else
   printf("no is not present: %d",k);

}
