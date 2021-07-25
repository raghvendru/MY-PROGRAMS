 #include<stdio.h>
int isRepeating(int a[],int n,int size,int index){
    int res=0;
    for(int i=0;i<size;i++){
        if(i!=index){
            if(a[i]==n){
                res=1;
                break;
            }
        }
    }
    return res;
}
#include<stdio.h>
int main(){
    int a[10]={1,4,1,2,0,1,3,5,6,7};
    int nr[10];
    int nrCnt=0;
    int res;
    for(int i=0;i<10;i++){
        if(isRepeating(a.a[i],10)==0)
        {
            nr[nrCnt]=a[i];
            nrCnt++;
        }
    }
    for(int i=0;i<nrCnt;i++){
        printf("%d",nr[i]);
    }
    return res;
}