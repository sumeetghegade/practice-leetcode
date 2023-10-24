package practice;


/*
INTUITION:
If no deletion was allowed we would solve this problem using Kadane's algorithm where we keep adding the numbers
but reset the sum if it goes negative.

If one deletion is allowed what we can do is perform kadanes algo starting from both right and left side.
We will store the sum upto that position in a seperate array.

so at any position 'i', if we want to know what would be the max subarray sum on skipping that number:
it can be calculated as : left[i-1] + right[i+1]

we get this value fo all negative numbers in the array and return the max.


 */
public class MaximumSubarraySumWithOneDeletion {

    public int maximumSum(int[] arr) {
        if(arr.length == 1) return arr[0];
        int n = arr.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        dp1[0] = arr[0];
        dp2[n-1] = arr[n-1];
        int max = arr[0];

        for(int i=1; i<n; i++){
            dp1[i] = dp1[i-1] > 0 ? dp1[i-1] + arr[i] : arr[i];
            max = Math.max(max, dp1[i]);
        }
        for(int i=n-2; i>=0; i--){
            dp2[i] = dp2[i+1] > 0 ? dp2[i+1] + arr[i] : arr[i];
        }

        for(int i=1; i<n-1; i++){
            if(arr[i] < 0){
                max = Math.max(max, dp1[i-1] + dp2[i+1]);
            }
        }
        return max;
    }
}
