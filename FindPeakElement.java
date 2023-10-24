package practice;

/*
INTUITION:
Brute force would be to check each element for if it is a peak element --> O(n)

But we can use the property of peak and the fact that the array has (-infinity) on both its sides.
so at any point there can be 3 cases for any element in the array
1. it is the peak element:
2. it is in an increasing sequence. so this will mean that the peak will lie to the right of current element
3. it is in a decreasing sequence. so this will mean that the peak will lie to the left of current element

this is true because we have -infinity at the ends. so if it is an increasing sequence and it keeps increasing till the end
this will mean that our last element is the peak
similar is true for a decreasing sequence.

so to make our algo efficiently find the peak we can use binary search.
in our binary search use the above conditions to return value or adjust left and right pointers.

 */

public class FindPeakElement {
    public static int findPeakElement(int[] nums) {


        int len = nums.length;
        // 1st edge case: only one element: means it is peak
        if(len == 1)
            return 0;

        // base case: first check for end elements.
        if(nums[0] > nums[1])
            return 0;
        else if(nums[len-1] > nums[len - 2])
            return len - 1;

        // if not found proceed with binary search
        // we checked for boundaries already. so no need to start at the ends of the array
        int left = 1; int right = len-2;


        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
                return mid;
            else if(nums[mid] < nums[mid -1])
                right = mid - 1;
            else if(nums[mid] > nums[mid-1] )
                left = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] ip = new int[] {3,4,3,2,1};
        System.out.println(findPeakElement(ip));
    }
}
