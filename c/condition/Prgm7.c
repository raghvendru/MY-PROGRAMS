
#include<stdio.h>
int main()
{
    int quantity = 0;
    printf("Enter quantity\n");
    scanf("%d",&quantity);

    if(quantity*100 > 1000){
        int cost  = (quantity*100)-(0.1*(quantity*100));
        printf("Your cost is %d\n", cost);
    }
    else{
        printf("Your cost is %d\n",quantity*100);
    }

}