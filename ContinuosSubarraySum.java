package practice;

import java.util.HashMap;

/*
INTUITION:
Brute force would be to check all subarrays: O(n^2)
Two pointer would be tricky as it will be very difficult to determine the window resize conditions.

We can use prefix sum in some way here as we are dealing with subarrays.
Lets take an example:
[22,  3,  2,  6,  3 ...] , k = 5
[22, 25, 27, 33, 36 ...]  --> Prefix Sum
[ 2,  0,  2,  3,  1, ...]

So, basically if we have a number 'x' and we mod it by k and get remainder r,
what number will we have to add to it to again get the same remainder?
we will need to add any number which is divisible by k to satisfy previous question.

going by the same principle, in our prefix sum if we keep dividing it by k and get the same remainder again means that
the numbers that we added after getting the remainder, their sum will be divisible by k.

we can start implementing based on this principle.
we also need to make sure that we have atleast 2 numbers in our answer so we will have to have access to the remainder as well as
index of previous remainders
lets use a hashmap to store the remainders and its index.


 */

public class ContinuosSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int prefixSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // edge case:
        // we need to handle the case where our first two elements sum is divisible by k
        // [0] [22,  3,  2,  6,  3 ...] --> makes it as if we have a 0 at the start
        map.put(0,-1);

        for(int i = 0; i < len; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;
            if(!map.containsKey(remainder))
                map.put(remainder, i);
            // make sure we have atleast 2 nums in our sum
            else if(i - map.get(remainder) > 1)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{24,4,4,6,2};
        System.out.println(checkSubarraySum(nums, 6));
    }
}
