#include <stdio.h>
int hcf(int n1, int n2) {
    if (n2 != 0)       //base condn,comp n decomp
        return hcf(n2, n1 % n2); 
    else
        return n1;
}


int main() {
    int n1=8, n2=6;
    printf("gcd %d and %d is %d.", n1, n2, hcf(n1, n2));
    return 0;
}

