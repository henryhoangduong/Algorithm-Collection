public class Main {

    public int[] solution(int[] A,int K) {
        for (int i = 0; i < K; i++) {
            int lastValue = A[A.length - 1];
            for (int j = A.length - 2; j >= 0; j--) {
                A[j + 1] = A[j];
            }
            A[0] = lastValue;

        }
        return A;
    }
     public static void main(String[] args) {
         Main main = new Main();
         int[] result = main.solution(new int[] { 1, 2, 3, 4, 5, 6 }, 3);
         for (int i : result) {
            System.out.println(i+" ");
         }
         
     }
}