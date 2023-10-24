package practice;

import java.util.Arrays;

/*
We can do brute force of:
1. Generate all permutations in sorted order (n!)
2. Do linear search on generated to find input array.
3. Return next array.
N! not acceptable.

So we can go for another approach.

1. Find long prefix (breakPoint)
In a dictionary if we observe,
the next word has some or most of its first chars same
which means that we need a long prefix.
This we can identify by finding the first peak starting from the right side of the array.
Example:
2 1 5 4 3 0 0
Prefix: 2 1
If we keep the prefix same, no matter how we rearrange the right subarray it wont be greater permutation than input.
Lets call this the breakPoint
so 1 --> breakPoint

2. Swap
Once we have found this, to generate the next permutation we need to change the breakPoint.
So which number from right subarray should  we choose to swap?
We need the smallest number greater than breakPoint.
If we take a number smaller than breakPoint we will get some previous permutation.
To find this number we just need to iterate from right and take the first number we find that is greater than breakPoint [as right subarray is sorted[increasing order]]

3. Once we have swapped we need the right subarray to be its smallest version.
This we can easily do by sorting it in decreasing order.
As it is already sorted in increasing order we can do sort by swapping first and last element for all elements.

 */

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        int breakPoint = -1;

        for(int i = len - 2; i >= 0; i--) {
            if(nums[i] < nums[i+1]){
                breakPoint = i;
                break;
            }
        }
        // edge case
        // again same logic for reverse as array already sorted. (lexicographically biggest, we return smallest)
        if(breakPoint == -1){
            reverse(nums, 0);
            return;
        }
        for(int i = len - 1; i >= breakPoint; i--) {
            if(nums[i] > nums[breakPoint]){
                swap(nums, i, breakPoint);
                break;
            }
        }
        reverse(nums, breakPoint + 1);
    }

    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while(i <= j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] ip = new int[]{2, 1, 5, 4, 3, 0, 0};
        NextPermutation obj = new NextPermutation();
        obj.nextPermutation(ip);
        System.out.println(Arrays.toString(ip));
    }
}
