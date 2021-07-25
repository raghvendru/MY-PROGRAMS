#include <stdio.h>
#include <conio.h>
#include <string.h>
void main() {
	char name[10][8],Tname[10][8],temp[8];
	int i,j,N;	
	printf("enter the number of strings\n");
	scanf("%d",&N);
	printf("enter %d names\n",N);
	for (i=0;i<N;i++) {
		scanf("%s",name[i]);
		strcpy (Tname[i],name[i]);
	}
	for (i=0;i< N-1;i++) {
		for (j=i+1;j<N;j++) {
			if(strcmp(name[i],name[j])>0) {
				strcpy(temp,name[i]);
				strcpy(name[i],name[j]);
				strcpy(name[j],temp);
			}
		}
	}
	for (i=0;i<N;i++) {
		printf("%s\t\t%s\n",Tname[i],name[i]);
	}
}