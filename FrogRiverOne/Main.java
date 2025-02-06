package FrogRiverOne;

import java.util.HashSet;

public class Main {
    public int solution(int X, int[] A) {
        HashSet<Integer> hSet = new HashSet<>();
        for (int i = 1; i <= X; i++) {
            hSet.add(A[i]);
        }
        for (int i = 0; i < A.length; i++) {
            if (hSet.remove(A[i])) {
                if (hSet.isEmpty()) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        
    }
}
