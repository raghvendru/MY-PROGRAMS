#include <stdio.h>

int main() {
    short v1 = 9 ;
    printf("value of v1 is %d,address is %p and size of short is %d \n",&v1, sizeof(short),v1);
    unsigned short v2 = 19 ;
    printf("value of v2 is %d,address is %p and size of unsigned short is %d \n",&v2, sizeof(unsigned short),v2);
    signed short v3= -2 ;
    printf("value of v3 is %d,address is %p and size of signed short is %d \n",&v3, sizeof(signed short),v3);

     int a1 = 9 ;
    printf("value of a1 is %d,address is %p and size of int is %d \n",&a1, sizeof(int),a1);
    unsigned int a2 = 20 ;
    printf("value of a2 is %d,address is %p and size of unsigned int is %d \n",&a2, sizeof(unsigned int),a2);
    signed int a3= -26;
    printf("value of a3 is %d,address is %p and size of signed int is %d \n",&a3, sizeof(signed int),a3);


     long b1 = 9 ;
    printf("value of b1 is %d,address is %p and size of long is %d \n",&b1, sizeof(long),b1);
    unsigned long b2 = 19 ;
    printf("value of b2 is %d,address is %p and size of unsigned long is %d \n",&b2, sizeof(unsigned long),b2);
    signed long b3= -8 ;
    printf("value of b3 is %d,address is %p and size of signed long is %d \n",&b3, sizeof(signed long),b3);
}
