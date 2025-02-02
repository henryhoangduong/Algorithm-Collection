// This is a demo task.

// Write a function:

// class Solution {
//     public int solution(int[] A);
// }

// that,

// given an
// array A
// of N integers,
// returns the
// smallest positive

// integer (greater than 0) that does not occur in A.

// For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

// Given A = [1, 2, 3], the function should return 4.

// Given A = [−1, −3], the function should return 1.

// Write an efficient algorithm for the following assumptions:

// N is an integer within the range [1..100,000];
// each element of array A is an integer within the range [−1,000,000..1,000,000].

import java.util.HashSet;

public class Main {
    public int solution(int[] A) {
        HashSet<Integer> nums = new HashSet<Integer>();
        for (int i = 1; i <= A.length + 1; i++) {
            nums.add(i);
        }
        for (int i : A) {
            nums.remove(Integer.valueOf(i));
        }
        return nums.iterator().next();
    }
    public static void main(String[] args) {
        
    }
}