

#include<stdio.h>
int main()
{
int p,c,m;
printf("enter value of p,c and m");
scanf(“%d %d %d”,&p,&c,&m);
if(p>=55 && c>=55  && m>=55 && p+c+m>=175) { 
printf("eligible");
}
else {
printf("not el");
}
return 0;
}