#include <stdio.h>
#include <stdlib.h>


void main()
{
   int arr1[50][50], brr1[50][50];
   int i, j, r1, c1, r2, c2, flag =1;
   
       printf("\n\nAccept two matrices and check whether they are equal :\n ");
       printf("-----------------------------------------------------------\n");   
  
   printf("Input Rows and Columns of the 1st matrix :");
   scanf("%d %d", &r1, &c1);

   printf("Input Rows and Columns of the 2nd matrix :");
   scanf("%d %d", &r2,&c2);
	 printf("Input elements in the first matrix :\n");
       for(i=0;i<r1;i++)
        {
            for(j=0;j<c1;j++)
            {
	           printf("element - [%d],[%d] : ",i,j);
	           scanf("%d",&arr1[i][j]);
            }
        } 
       printf("Input elements in the second matrix :\n");
       for(i=0;i<r2;i++)
        {
            for(j=0;j<c2;j++)
            {
	           printf("element - [%d],[%d] : ",i,j);
	           scanf("%d",&brr1[i][j]);
            }
        }   
 	 printf("The first matrix is :\n");
	 for(i=0;i<r1;i++)
	 {
	   for(j=0;j<c1 ;j++)
	     printf("% 4d",arr1[i][j]);
	    printf("\n");
	 }
	 printf("The second matrix is :\n");
	 for(i=0;i<r2;i++)
	 {
	   for(j=0;j<c2 ;j++)
	     printf("% 4d",brr1[i][j]);
	    printf("\n");
	 }
   /* Comparing two matrices for equality */

   if(r1 == r2 && c1 == c2)
   {
    	printf("The Matrices can be compared : \n");
    	for(i=0; i<r1; i++)
    	{
     		for(j=0; j<c2; j++)
     		{
			if(arr1[i][j] != brr1[i][j])
			{
	   			flag = 0;
	   			break;
			}
     		}
   	   }
    }
    else
    {  printf("The Matrices Cannot be compared :\n");
       exit(1);
    }
    if(flag == 1 )
	printf("Two matrices are equal.\n\n");
    else
	printf("But,two matrices are not equal\n\n");
}