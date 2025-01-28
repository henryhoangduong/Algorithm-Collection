

import java.util.ArrayList;
import java.util.List;

public class OddOccurrencesInArray {
    List<Integer> aList = new ArrayList<Integer>();
    public int solution(int[] A){
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            if (aList.contains(a)) {
                aList.remove(Integer.valueOf(a));
            } else {
                aList.add(Integer.valueOf(a));
            }
        }
        return aList.get(0);
    }
    public static void main(String[] args) {
        OddOccurrencesInArray occurrencesInArray = new OddOccurrencesInArray();
        int res = occurrencesInArray.solution(new int[] { 9, 3, 9, 3, 9, 7, 9 });
        System.out.println(res);
    }
}