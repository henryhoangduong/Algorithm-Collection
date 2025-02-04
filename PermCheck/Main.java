import java.util.HashSet;

public class Main {
    public int solution(int[] A) {
        HashSet<Integer> nums = new HashSet<>();
        for (int i = 1; i < A.length + 1; i++) {
            nums.add(Integer.valueOf(i));
        }
        for (int a : A) {

            if (!nums.contains(a)) {
                return 0;
            } else {
                nums.remove(a);
            }
        }

        return nums.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        Main pc = new Main();
        System.out.println(pc.solution(new int[] { 4, 1, 3, 2 }));
    }
}