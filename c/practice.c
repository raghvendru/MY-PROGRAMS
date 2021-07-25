#include<stdio.h>
void main()
{
int i, j;
int arr[2][2]=   
		{
		    {11, 12},{14, 15}
		};
printf("2d Elements\n\n");
for(i=0;i<2;i++)
{
	for(j=0;j<2;j++)
	{
		printf("%d\t",arr[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}