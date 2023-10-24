package practice;

import java.util.HashMap;


/*
INTUITION:
The brute force way to do this is to check every subarray which would take (n^3).

While doing this we are doing some repeated work as we can get the sum of a group of elements in the middle of the array
by subtracting the sum calculated upto the element just before the start of the group from the total sum up to last element in the group.

0 1 2 3 4 5
1 1 1 1 1 1
1 2 3 4 5 6

sum(3-4) = sum(0-4) - sum(0-2)

So basically, when we are calculating the prefix sum all we need to find out is:
Are there any prefixes in the array that we can chop off to get the sum == k.

to find the prefix efficiently we can store the prefix in a map.
we also need to maintain the prefix count as we need to account for -ve numbers which can lead to multiple subarray giving same sum k.



 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {

        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // edge case: if first few elements sum up to k then we dont have anything in our map to account for this.
        // so this is like adding an imaginary prefix sum equal to 0 in the map to make sure we dont miss this case.
        map.put(0,1);

        for(int i: nums) {
            sum = sum + i;
            if(map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


}
