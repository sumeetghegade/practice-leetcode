package practice;

import java.util.HashSet;
import java.util.Set;

/*
Two approaches:
1. Straightforward:
Add numbers to a set.
Start counting from 1 to arr.length + k.
keep a count of missing numbers. return kth number

2. Binary search
Numbers are sorted so we can definitely make use of that.
We can calculate the missing numbers till index 'i' using arr[i] - i - 1;

INDEX:              0  1  2  3   4
GIVEN:              2  3  4  7  11
IF NO MISSING:      1  2  3  4  5
No. OF MISSING:     1  1  1  3  6

Final pointers:              r  l  --> binary search breaks here

So basically we are performing binary search on the no of missing array.
When our binary search will end we will be at a point where the missing number will be somewhere to the right of right pointer.
we know what number would be present at 'r' if no number was missing. so our missing number would be:
"number present at r if no missing + k"


 */


public class KthMissingPositiveNumber {

    // O(n + k)
    public int findKthPositive1(int[] arr, int k) {

        Set<Integer> set = new HashSet<>();
        for(int num: arr) {
            set.add(num);
        }
        int count = 0;
        for(int i = 1; i <= k + arr.length; i++) {
            if(!set.contains(i))
                count++;
            if(count == k)
                return i;
        }
        return 0;
    }

    // O(logn)
    public int findKthPositive2(int[] arr, int k) {

        int left = 0, right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] - mid - 1 < k)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right + 1 + k;

    }
}
