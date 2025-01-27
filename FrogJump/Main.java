package FrogJump;

public class Main {
    public int solution(int x, int y, int D) {
        int distance = y - x;
        System.out.println(distance / (double)D);
        int jumps = (int) Math.ceil(distance / D);
        return jumps;
    }
    public static void main(String[] args) {
        System.out.println(new Main().solution(10, 85, 30));
    }
}
