#include

int equilibrium_index(int arr[], int n)
{
int i, j;
int leftsum, rightsum;
for (i = 0; i < n; ++i) {
leftsum = 0;
for (j = 0; j < i; j++)
leftsum += arr[j];
rightsum = 0;
for (j = i + 1; j < n; j++)
rightsum += arr[j];
if (leftsum == rightsum)
return i;
}
return -1;
}
int main()
{
int n;
printf("\nEnter the number of elements : ");
scanf("%d",&n);
int arr[n];
printf("\nInput the array elements : ");
for(int i = 0; i < n; i++)
{
scanf("%d",&arr[i]);
}
printf("\nEquilibrium Index : %d\n", equilibrium_index(arr, n));
return 0;
}