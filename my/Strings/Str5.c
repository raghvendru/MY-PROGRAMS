/* C Program to Remove All Duplicate Character in a String */
 
#include <stdio.h>
#include <string.h>
 
int main()
{
  	char str[100];
  	int i, j, k;
 
  	printf("\n Please Enter any String :  ");
  	gets(str);
  	 	
  	for(i = 0; i < strlen(str); i++)
  	{
  		for(j = i + 1; str[j] != '\0'; j++)
  		{
  			if(str[j] == str[i])  
			{
  				for(k = j; str[k] != '\0'; k++)
				{
					str[k] = str[k + 1];
				}
 			}
		}
	}
	
	printf("\n The Final String after Removing All Duplicates = %s ", str);
	
  	return 0;
}

/*

str[] = helloh
ch = l

First For Loop – First Iteration: for(i = 0; i < strlen(str) ; i++)
The condition is True because 0 < 6.
Next, it will enter into Second For Loop


 
Second For Loop – First Iteration: for(j = i + 1; str[j] != ‘\0’; j++)
=> for(j = 1; e != ‘\0’; 1++)

Within the C Programming For Loop, we used If statement to check whether the str[1] is equal to str[0]

if(str[j] == str[i]) => if(e == h)

The above condition is false. So, i will increment.

Second For Loop – Second Iteration: for(j = 2; l != ‘\0’; 2++)

if(str[j] == str[i]) => if(l == h) – condition is false. So, i will increment.

Second For Loop – Third Iteration: for(j = 3; l != ‘\0’; 3++)


 
if(str[j] == str[i]) => if(l == h) – condition is false. So, i incremented.

Second For Loop – Fourth Iteration: for(j = 4; o != ‘\0’; 4++)
if(str[j] == str[i]) => if(o == h) – condition is false. So, i will increment.

Second For Loop – Fifth Iteration: for(j = 5; h != ‘\0’; 5++)

if(str[j] == str[i]) => if(h == h)

The above condition is True. So, it will enter into third For Loop

Third For Loop – First Iteration: for(k = j; str[k] != ‘\0’; k++)
=> for(k = 5; str[5] != ‘\0’; 5++)
str[k] = str[k + 1];
str[k] = ‘\0’

Third For Loop – Second Iteration: for(k = 6; ‘\0’ != ‘\0’; 6++)
Condition fails. So, it will exit from the Third For Loop.

Do the same for remaining iterations.

At last, we used the printf statement to print the final string

*/