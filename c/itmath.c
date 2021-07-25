  
int fact(int n) {
    int i,fact=1;
    for(i=1;i<=n;i++) {
        fact=fact*i;
    }    
    return fact;  
}   

int isSTRONG(int n) {
    int i;
    int sum=0;
    int temp=n;
    while(temp>0) {
    int digit=temp%10;
    sum=sum+fact(digit);
    temp=temp/10;
    }    
    if (sum==n) {
       return 1;
    }
   else {
           return 0;

        }
    
    }
    
 