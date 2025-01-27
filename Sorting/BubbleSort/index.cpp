#include <vector>
#include <math.h>
#include <stdio.h>
#include <iostream>  
using namespace std;

void bubbleSort(int arr[], int n){
    bool swapped=true;
    while(swapped){
        swapped=false;        
    for (int i=0;i<n-1;i++){
        if(arr[i]>arr[i+1]){
            swap(arr[i],arr[i+1]);
            swapped=true;
        }
     }
    }

}
void printArray(int arr[], int n)
{
    for (int i = 0; i < n; ++i)
    cout << arr[i] << " ";
    cout << endl;
}
int main() {
    int arr[] = { 12, 11,34,60,2,10,4, 13, 5, 6 };
    int n = sizeof(arr) / sizeof(arr[0]);
    bubbleSort(arr,n);
    printArray(arr, n);

    return 0;
}