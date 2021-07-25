#include <stdio.h>
int main() {
    int n1,n2,lcm=1,num,n,m;
    printf("enter two num \n");
    scanf("%d %d",&n1,&n2);
    num=2;
    n=n1;
    m=n2;
    printf("%d",num);
    while(n1>1 || n2>1){
        if (n1%num==0 && n2%num==0) {
    n1=n1/num;
    n2=n2/num;
    lcm*=num;
    }


  else if (n2%num==0){
    n2=n2/num;
    lcm*=num;
}
    else if (n1%num==0){
    n1=n1/num;
    lcm*=num;
    }
    else{
        num++;
    }
    }
    printf("lcm is %d",n,m,lcm);
    
}