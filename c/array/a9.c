#include<stdio.h>
struct FreqCard{
    int num;
    int freq;
};
void main(int argc,char*argv[]) {
    int n,numfreq=0;
    printf("enter size");
    scanf("%d",&n);
    int arr[n];
    struct FreqCard freq[n];
    for(int i=0;i<n;i++) {
        scanf("%d",&arr[i]);
    }
    for(int i=0;i<n;i++){
        if(numfreq==0){
            
                freq[numfreq].num==arr[i];
                freq[numfreq].freq=1;
                numfreq++;
            }
            else{
                int isNew=0;
                for(int j=0;j<numfreq;j++){
                    if(arr[i]==freq[j].freq+1){
                        freq[j].freq=freq[j].freq+1;
                        isNew=1;
                        break;
                    }
                }
                if(isNew==0){
                    freq[numfreq].num=arr[1];
                    freq[numfreq].freq=1;
                    numfreq++;
                }
            }
        }
        for(int j=0;j<numfreq;j++){
            if(freq[j].freq>1){
                printf("%d %d\n",freq[j].num,freq[j].freq);
            }
        }
    }
