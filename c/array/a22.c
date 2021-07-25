#include<stdio.h>
void addition(int m[3][3], int n[3][3]);
int main()
{

  int a[3][3], b[3][3], i, j;

  // take first matrix
  for(i=0; i<3; i++)
  {
    for(j=0; j<3; j++)
    {
      printf("Enter a[%d][%d]:",i,j);
      scanf("%d",&a[i][j]);
    }
  }
  printf("\n");

  // take second matrix
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      printf("Enter b[%d][%d]:",i,j);
      scanf("%d",&b[i][j]);
    }
  }


  addition(a,b); // matrix a and b passed

  return 0;
}

void addition(int m[3][3], int n[3][3])
{
  int sum[3][3], i, j;

  // add both matrices
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      sum[i][j] = m[i][j] + n[i][j];
    }
  }

  // print resultant matrix
  printf("\nAddition of both Matrix is:\n");
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      printf("%d\t",sum[i][j]);
    }
    printf("\n"); // new line
  }
}