package practice;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    // simple sort
    // nlogn
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // priority queue
    // O(n*log(k))
    public int findKthLargest2(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);

        for(int i: nums) {
            pq.add(i);
            if(pq.size() > k)
                pq.poll();
        }

        return pq.poll();

    }

    // quick select
    // average: O(n) worst: O(n^2)
    // kind of use the idea from quicksort but we dont need to sort the entire array,
    // we only need to perform quicksort till we place the correct element at kth position
    // in quickselect we select a pivot element at random and try to move it such that it reaches it correct
    // spot in the sorted array.
    // we can build on this principle.
    //
    public int findKthLargest3(int[] nums, int k) {
        // we'll implement a recursive function that keeps choosing pivot elements and puts them at correct place
        // once we have done that, we can decide based on the index we are at and the k value if we want to move left or right
        return quickselect(nums, nums.length - k, 0, nums.length - 1);

    }



    public int quickselect(int[] nums, int k, int left, int right) {
        int pivot = nums[right];
        int pointer = left;

        for(int i = left; i < right; i++) {
            if(nums[i] <= pivot) {
                swap(nums, pointer, i);
                pointer++;
            }
        }
        swap(nums, pointer, right);

        if(pointer > k)
            return quickselect(nums, k, left, pointer-1);
        else if(pointer < k)
            return quickselect(nums, k, pointer + 1, right);
        else
            return nums[pointer];
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
