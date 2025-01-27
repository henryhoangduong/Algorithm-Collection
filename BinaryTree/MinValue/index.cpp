#include <iostream>
using namespace std;

struct node {
    int data;
    struct node* left;
    struct node* right;
};

struct node* NewNode(int data) {
    struct node* node = new struct node; 
    node->data = data;
    node->left = NULL;
    node->right = NULL;
    return node;
}

int minValue(struct node *root){
    if(root != NULL){
        return ;
    }
    int lefval = minValue(root->left);
    int rightval = minValue(root->right);
    return min(lefval,rightval);
}

int insertTree(struct node *root){
    
}

int main(){
    
    return 0;
}