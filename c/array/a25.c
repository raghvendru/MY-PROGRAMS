#include<stdio.h>
void transpose(int m[3][3]);
int main()
{
  int a[3][3], trans[3][3], i, j;
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      printf("Enter a[%d][%d]:",i,j);
      scanf("%d",&a[i][j]);
    }
  }
  transpose(a); 
  return 0;
}

//transpose method
void transpose(int m[3][3])
{
  int trans[3][3], i, j;
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      trans[i][j]=m[j][i];
    }
  }

  
  printf("Transpose of Matrix is:\n");
  for(i=0;i<3;i++)
  {
    for(j=0;j<3;j++)
    {
      printf("%d\t",trans[i][j]);
    }
    printf("\n"); 
  }
}