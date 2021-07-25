#include <stdio.h>
#include <string.h>

#define MAX_BOOK_NUM 1000
struct book {
    char title[128];
    int  year ;
    int  num ; 
} ;

struct book bks[MAX_BOOK_NUM] ;
    
void init() {
    strcpy(bks[0].title, "title1");
    // strcpy(bks[0].author, "author1");
    bks[0].num = 1982;
    bks[0].year = 1982;
    // bks[0].numOfBooks = 12;
    // bks[0].stock = 12 ;
    strcpy(bks[1].title, "title2");
    // strcpy(bks[1].author, "author2");
    bks[1].num = 1982;
    bks[1].year = 1982;
    // bks[1].numOfBooks = 12;
    // bks[1].stock = 12 ;
    strcpy(bks[2].title, "title3");
    // strcpy(bks[2].author, "author3");
    bks[2].num = 1982;
    bks[2].year = 1982;
    // bks[2].numOfBooks = 12;
    // bks[2].stock = 12 ;
    return ;
}

void write() {
    FILE *fp = fopen("books1.data","w") ;
    if (fp != NULL) {
        fwrite(&bks, sizeof(struct book), 3, fp) ; 
        printf("Saved to file!!!\n");
    }
    fclose(fp);
    return ;
}

void read() {
    FILE *fp = fopen("books1.data","r") ;
    struct book bk[3];
    if (fp != NULL) {
        fread(&bk, sizeof(struct book), 3, fp) ; 
    }
    fclose(fp);
    printf("%s\n",bk[0].title);
    printf("%d\n",bk[0].num);
    printf("%d\n",bk[0].year);
    printf("%s\n",bk[1].title);
    printf("%d\n",bk[1].num);
    printf("%d\n",bk[1].year);
    printf("%s\n",bk[2].title);
    printf("%d\n",bk[2].num);
    printf("%d\n",bk[2].year);
    return ;
}

int main() {
    // init();
    // write();
    read();
    return 0;
}
