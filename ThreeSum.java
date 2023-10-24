package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
INTUITION:
We definitely need to sort the array. sorting will make our traversal and find easier in terms of run time.
After sorting we can use a two pointer approach or a hashmap approach
For both the idea will be to fix one out of the three numbers and then find the remaining two numbers for that number.

So we can iterate on the array, we will skip duplicates as no duplicates are allowed in the result.
For each number:
1. Fix left and right pointer. left will be cur_position + 1 and right will be nums.length - 1;
2. We get the sum of nums[curpos] + nums[left] + nums[right];
3. If it is zero add the numbers to result.
    increment left(again skip duplicates)
4. If > zero: we need to reduce sum so decrement right pointer
   If < zero: we need to increase sum so increment left
5. After left >= right we stop and return out final list

 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if(nums.length < 3){    //Base case 1
            return result;
        }
        if(nums[0] > 0){        //Base case 2
            return result;
        }

        Arrays.sort(nums);
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            // skip duplicates
            if(i > 0 && nums[i] == nums[i-1])
                continue;

            int left = i+1; int right = len - 1;

            while(left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                if(threeSum > 0)
                    right--;
                else if(threeSum < 0)
                    left++;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while(nums[left] == nums[left-1] && left < right)
                        left++;
                }
            }
        }
        return result;
    }
}
