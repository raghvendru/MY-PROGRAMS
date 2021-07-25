#include <stdio.h>
#include <stdlib.h>

// Create a structure 
struct Node {
    int data ;
    struct Node *next ;
} ;

void insert(struct Node *node , struct Node **head) {
    if (*head == NULL) {
        *head = node ;
        return;
    }
    struct Node *temp ;
    temp = *head ;
    while (temp->next != NULL) {
        printf("temp is %p \n", temp);
        temp = temp->next ;
    }
    printf("inserting at %p \n", temp);
    temp->next = node ;
    return ;
} 

void insertAtValue(struct Node *node , struct Node **head, int data) {
    struct Node *temp ;
    temp = *head ;
    while (temp->data != data) {
        temp = temp->next ;
    }
    printf("inserting at %p \n", temp);
    node->next = temp->next;
    temp->next = node ;
    return ;
}

void traverse(struct Node *head) {
    if (head == NULL) {
        printf("list is empty \n");
        return;
    }
    struct Node *temp ;
    temp = head ;
    while (temp != NULL) {
        printf("%d \n", temp->data);
        temp = temp->next ;
    }

    return ;
} 

void delete(struct Node *head, int data) {
    if (head == NULL) {
        printf("list is empty \n");
        return;
    }
    struct Node *temp, *prev ;
    temp = head ;
    prev = head ;
    while (temp != NULL && temp->data != data) {
        prev = temp ;
        temp = temp->next ;
    }
    if (temp == NULL ) {
        printf("could not fine the node with data %d  \n", data);
        return ;
    } else { // temp sould contian the data and prev contians previoius to data
        prev->next = temp->next ;
        free(temp);
    }
    return ;
}

int main(int argc, char *argv[]) {
    struct Node *head = NULL; 
    for (int i =0; i<7;i++) {
        struct Node *node = (struct Node *) malloc(sizeof(struct Node));
        node->data = i;
        node->next = NULL ;
        printf("inserting %d into list with node %p head %p \n", i, node, head);
        insert(node, &head);    
    }
    struct Node *node = (struct Node *) malloc(sizeof(struct Node));
    node->data = 12;
    node->next = NULL ;
    insertAtValue(node, &head, 4);
    traverse(head);
    delete(head, 2);
    traverse(head);
}

