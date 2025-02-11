package BestTimeToBuyAndSellStockII;

// You are given an integer array prices where prices[i]is the price of a given stock on the ith day.

// On each day,you may decide to buy and/or sell the stock.You can only hold at most one share of the stock at any time.However,you can buy it then immediately sell it on the same day.

// Find and return the maximum profit you can achieve.
public class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0; // Initialize total profit to zero

        // Loop through the array of prices
        for (int i = 1; i < prices.length; ++i) {
            // Calculate the profit for the current day by subtracting the previous day's
            // price from the current day's price
            int dailyProfit = Math.max(0, prices[i] - prices[i - 1]);

            // Add the daily profit to the total profit
            // This will accumulate if buying on the i-1 day and selling on the i day is
            // profitable
            totalProfit += dailyProfit;
        }

        // Return the total profit accumulated
        return totalProfit;
    }

    public static void main(String[] args) {

    }
}
