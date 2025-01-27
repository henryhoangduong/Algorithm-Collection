import java.util.HashSet;

public class Main {
    public int solution(int[] A){
        HashSet<Integer> lIntegers = new HashSet<>();
        for (int i = 1; i < A.length+1; i++) {
            lIntegers.add(i);
        }
        for (int i = 0; i < A.length; i++) {
            lIntegers.remove(Integer.valueOf(A[i]));
        }
        return lIntegers.iterator().next();

    }
    public static void main(String[] args) {
        System.out.println(new Main().solution(new int[] {2,3,1,5}));
    }
}