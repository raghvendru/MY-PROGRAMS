#include <stdio.h>
void main()
{
   long int num,re ;
   int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0;
   printf("enter any number : ");
   scanf("%d",&num);
   while(num>0) {
       re=num%10;
       num=num/10;
   switch(re)

   {
	case 0:
	      a++;
	       break;
	case 1:
	       b++;
	       break;
	case 2:
	       c++;
	       break;
	case 3:
	       d++;
	       break;
	case 4:
	       e++;
	       break;
	case 5:
	       f++;
	       break;
	case 6:
	       g++;
	       break;
	case 7:
	       h++;
	       break;
	case 8:
	       i++;
	       break;
	case 9:
	       j++;
	       break;
	
	default:
	       printf("invalid num. \nPlease try again ....\n");
	       
      }
}
 printf("o repeating %d times  \n",a);
 printf("1 repeating %d times  \n",b);
 printf("2 repeating %d times  \n",c);
 printf("3 repeating %d times  \n",d);
 printf("4 repeating %d times  \n",e);
 printf("5 repeating %d times  \n",f);
 printf("6 repeating %d times  \n",g);
 printf("7 repeating %d times  \n",h);
 printf("8 repeating %d times  \n",i);
 printf("9 repeating %d times  \n",j);
}