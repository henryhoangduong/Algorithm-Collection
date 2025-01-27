#include <iostream>
#include <vector>
#include <list>

using namespace std;

class MyHashSet {
private:
    // Vector of linked lists (buckets)
    vector<list<int> > buckets;
    // Hash set size (number of buckets)
    int size;

    // Hash function to map key to an index
    int hash(int key) {
        return key % size; // Simple modulo hash function
    }

public:
    // Constructor to initialize the hash set with a fixed size
    MyHashSet() {
        size = 1000; // You can adjust the size based on expected data size
        buckets.resize(size);
    }

    // Add a key to the hash set
    void add(int key) {
        int index = hash(key);
        // Check if the key is already in the bucket, if not, add it
        for (list<int>::iterator it = buckets[index].begin(); it != buckets[index].end(); ++it) {
            if (*it == key) {
                return; // Key already exists, no need to add it
            }
        }
        buckets[index].push_back(key);
    }

    // Check if a key exists in the hash set
    bool contains(int key) {
        int index = hash(key);
        // Search for the key in the bucket using an iterator
        for (list<int>::iterator it = buckets[index].begin(); it != buckets[index].end(); ++it) {
            if (*it == key) {
                return true;
            }
        }
        return false;
    }

    // Remove a key from the hash set
    void remove(int key) {
        int index = hash(key);
        // Iterate through the bucket to find and remove the key
        buckets[index].remove(key);
    }
};

int main() {
    MyHashSet myHashSet;

    myHashSet.add(1);      // set = [1]
    myHashSet.add(2);      // set = [1, 2]
    cout << myHashSet.contains(1) << endl; // returns true
    cout << myHashSet.contains(3) << endl; // returns false
    myHashSet.add(2);      // set = [1, 2]
    cout << myHashSet.contains(2) << endl; // returns true
    myHashSet.remove(2);   // set = [1]
    cout << myHashSet.contains(2) << endl; // returns false

    return 0;
}
