package Product_of_Array_Except_Self;

public class Main {
    // public int[] solution(int[] nums) {
    // int length = nums.length;
    // int[] result = new int[length];
    // int leftProduct = 1;
    // for (int i = 0; i < length; i++) {
    // // Set the current element in the result array to 'leftProduct'
    // result[i] = leftProduct;
    // // Multiply 'leftProduct' by the current element in nums for the next
    // iteration
    // // (prefix product)
    // leftProduct *= nums[i];
    // }
    // }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        int length = nums.length;
        int[] result = new int[length];
        int leftProduct = 1;
        for (int i = 0; i < length; i++) {
            System.out.println("i: " + i);
            result[i] = leftProduct;
            System.out.println("result of i: " + result[i]);
            leftProduct *= nums[i];
            System.out.println("leftProduct: " + leftProduct);
        }
        int rightProduct = 1;
        // Loop through the array from right to left
        for (int i = length - 1; i >= 0; i--) {
            // Multiply the current element in the result array by 'rightProduct'
            System.out.println("i: " + i);
            result[i] *= rightProduct;
            System.out.println("result of i: " + result[i]);

            // Multiply 'rightProduct' by the current element in nums for the next iteration
            // (suffix product)
            rightProduct *= nums[i];
            System.out.println("rightProduct: " + rightProduct);

        }
    }

}
