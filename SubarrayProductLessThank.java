package practice;


/*
INTUITION:
Brute we can do by creating all possible subarrays and calculating their product and counting the ones that have product < k.

But instead of doing the repetitive work, we can instead also use a sliding window to maintain a window with product always < k.
we maintain a left pointer and a right pointer.
rigth pointer keeps incrementing.
left pointer increments if product goes bigger than or equal to k.

The number of subarrays ending at index j and satisfying the condition can be calculated as (j - i + 1).
This is because, within the current subarray ending at j,
you can have a subarray of length 1, a subarray of length 2, and so on, up to j - i + 1.
All of these subarrays have a product less than k



 */

public class SubarrayProductLessThank {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0, j = 0;
        int n = nums.length;
        int result = 0;
        int prod = 1;
        while(j < n) {
            prod = prod * nums[j];
            while(i <= j && prod >= k) {
                prod = prod / nums[i];
                i++;
            }
            result += j - i + 1;
            j++;
        }

        return result;
    }
}
