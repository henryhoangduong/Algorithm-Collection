import java.util.*;

public class combinations {
    // Function to generate all combinations of size k
    public static void combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        backtrack(n, k, 1, currentCombination, result);

        // Print all combinations
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    // Backtracking function to find combinations
    private static void backtrack(int n, int k, int start, List<Integer> currentCombination,
            List<List<Integer>> result) {
        // If the current combination has the required size
        if (currentCombination.size() == k) {
            System.out.println("currentCombination" + currentCombination);
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Explore further by adding elements to the current combination
        for (int i = start; i <= n; i++) {
            currentCombination.add(i); // Choose element i
            backtrack(n, k, i + 1, currentCombination, result); // Recurse with the next element
            currentCombination.remove(currentCombination.size() - 1); // Backtrack by removing element i
        }
    }

    public static void main(String[] args) {
        int n = 4; // Total elements (1 to n)
        int k = 2; // Size of each combination
        combine(n, k);
    }
}
