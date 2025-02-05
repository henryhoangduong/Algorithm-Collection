import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    private List<List<Integer>> combinations = new ArrayList<>(); // Store the list of all combinations
    private List<Integer> currentCombination = new ArrayList<>(); // Current combination being explored
    private int[] candidateNumbers; // Array of candidate numbers

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort the array of candidates to optimize the process
        this.candidateNumbers = candidates; // Store the global reference for candidate numbers
        backtrack(0, target);
        return combinations;
    }

    private void backtrack(int startIndex, int remainingSum) {
        if (remainingSum == 0) {
            // If the remaining sum is 0, we found a valid combination
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }
        if (startIndex >= candidateNumbers.length || remainingSum < candidateNumbers[startIndex]) {
            // If startIndex is out of bounds or the smallest candidate exceeds remainingSum
            return;
        }

        // Skip the current candidate and move to the next one
        backtrack(startIndex + 1, remainingSum);

        // Include the current candidate in the current combination
        currentCombination.add(candidateNumbers[startIndex]);
        // Continue exploring with the current candidate (since we can use the same
        // number multiple times)
        backtrack(startIndex, remainingSum - candidateNumbers[startIndex]);
        // Backtrack and remove the last element before trying the next candidate
        currentCombination.remove(currentCombination.size() - 1);
    }

    public static void main(String[] args) {

    }
}