// C program to find all the triplets with the given sum

#include

void find_all_triplets(int arr[], int n, int sum)
{
for (int i = 0; i < n – 2; i++)
{
for (int j = i + 1; j < n – 1; j++)
{
for (int k = j + 1; k < n; k++)
{
if (arr[i] + arr[j] + arr[k] == sum)
{
printf(“%d,%d,%d\n”,arr[i],arr[j],arr[k]);
}}}}}

int main()
{
int n, sum;
printf(“\nEnter the number of elements : “);
scanf(“%d”,&n);
int arr[n];
printf(“\nInput the array elements : “);
for(int i = 0; i < n; i++)
{
scanf(“%d”,&arr[i]);
}
printf(“\nEnter the sum value : “);
scanf(“%d”,&sum);
printf(“\nThe triplets are \n “);
find_all_triplets(arr, n, sum);
return 0;
}