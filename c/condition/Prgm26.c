

#include <stdio.h>
void main()
{
   char grade;
   printf("Input grade: ");
   scanf("%c",&grade);
   switch(grade)
   {
	case 'E':
	       printf("EXXCELLENT \n");
	       break;
	case 'V':
	       printf("VERY GOOD \n");
	       break;
	case 'G':
	       printf("GOOD \n");
	       break;
    }
}