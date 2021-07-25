#include <stdio.h>


int main()
{
int m,n,p,q,i,j,k,sum=0;
int mat1[10][10],mat2[10][10],mat3[10][10]; //m3 and k for result

//ask user and read
printf("Enter no of r and c mat1\n");
scanf("%d %d", &m, &n);
printf("Enter elements matrix1\n");
for (i = 0;i<m;i++)
for (j = 0;j<n; j++)
scanf("%d", &mat1[i][j]);
printf("Enter number of r and c mat2\n");
scanf("%d %d", &p, &q);

if (n != p)   //impossible condition
printf("The matrices can not be multiplied\n");

else //posssible condition
{
printf("Enter elements of matrix2\n");
for(i=0;i<p;i++)
for(j=0;j<q;j++)
scanf("%d", &mat2[i][j]);
for(i=0;i<m;i++){       //you can take index p or m for k in because multi possible on m==p
for(j=0;j<q;j++){
for(k=0;k<p;k++){
sum=sum+mat1[i][k]*mat2[k][j]; //multiplication and add
}
mat3[i][j]=sum;
sum =0;
}
}


//print result
printf("Product is\n");
for(i=0;i<m;i++){
for(j=0;j<q;j++)
printf("%d\t",mat3[i][j]);
printf("\n");
}
}
return 0;
}