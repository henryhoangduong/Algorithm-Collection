import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> solution(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // Temporary list to store the values of nodes at the current level.
            List<Integer> level = new ArrayList<>();
            // Process all nodes at the current level.
            int levelLength = queue.size();
            for (TreeNode t : queue) {
                System.out.print(t.val + " ");
            }
            System.out.println();
            for (int i = 0; i < levelLength; ++i) {
                // Retrieve and remove the head of the queue.
                TreeNode currentNode = queue.poll();

                // Add the node's value to the temporary list.
                level.add(currentNode.val);

                // If the left child exists, add it to the queue for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // If the right child exists, add it to the queue for the next level.
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add the temporary list to the result list.
            result.add(level);
        }

        // Return the list of levels.
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode leaf1 = solution.new TreeNode(4);
        TreeNode leaf2 = solution.new TreeNode(5);
        TreeNode leaf3 = solution.new TreeNode(6);
        TreeNode leaf4 = solution.new TreeNode(7);

        // Level 2
        TreeNode node2 = solution.new TreeNode(2, leaf1, leaf2);
        TreeNode node3 = solution.new TreeNode(3, leaf3, leaf4);

        // Level 1 (Root node)
        TreeNode root = solution.new TreeNode(1, node2, node3);

        solution.solution(root);
    }
}