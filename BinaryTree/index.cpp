#include <stdio.h>
#include <iostream>
using namespace std;  

struct node {
   int data;
   struct node *left;
   struct node *right;
};

struct node *createNode(int val) {
   struct node *temp = (struct node *)malloc(sizeof(struct node));
   temp->data = val;
   temp->left = temp->right = NULL;
   return temp;
}

struct node* insertNode(struct node* node, int val) {
   if (node == NULL) return createNode(val);
   if (val < node->data)
   node->left = insertNode(node->left, val);
   else if (val > node->data)
   node->right = insertNode(node->right, val);
   return node;
}

void postOrder(struct node* root){
    if(root==NULL){
        return;
    }
    postOrder(root->right);
    postOrder(root->left);
    std::cout<< root->data<< " ";;
}

int main(){
    struct node *root = NULL;
   root = insertNode(root, 4);
   insertNode(root, 5);
   insertNode(root, 2);
   insertNode(root, 9);
   insertNode(root, 1);
   insertNode(root, 3);
   postOrder(root);
    return 0;
}