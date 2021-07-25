
#include <stdio.h>
int main() {
    int att,class,pf;
    printf("Enter att and class: ");
    scanf("%d %d", &att,&class);

    pf=(att/class)*100;
    if(pf > 75){
        printf("%d is eligible",pf);
    }
    else{
        printf("%d not eli",pf);
    }

    
}