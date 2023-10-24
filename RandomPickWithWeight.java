package practice;

import java.util.Random;

public class RandomPickWithWeight {

    static class Solution {

        private int[] cumulativeSum;
        private int sum;
        Random rand;

        public Solution(int[] w) {
            this.cumulativeSum = new int[w.length];
            this.sum = 0;
            this.rand = new Random();
            for(int i = 0; i < w.length; i++) {
                cumulativeSum[i] = sum + w[i];
                sum = cumulativeSum[i];
            }
        }
        public int pickIndex() {

            int n = this.rand.nextInt(this.sum) + 1;   // (1, sum)

            // Linear approach (remove +1 from previous line)
            // for(int i = 0 ; i < this.cumulativeSum.length; i++) {
            //     if(n < cumulativeSum[i])
            //         return i;
            // }
            // return -1;

            // optimized binary search
            int l = 0; int r = cumulativeSum.length - 1;


            while (l <= r) {
                int mid = l + (r - l) / 2;

                // found exact match
                if(cumulativeSum[mid] == n)
                    return mid;
                if(cumulativeSum[mid] < n)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            // no match found, hence return left element index.
            return l;
        }
    }

    public static void main(String[] args) {
        int[] w = new int[]{1,2,3,4,5};
         Solution obj = new Solution(w);
         int param_1 = obj.pickIndex();
        System.out.println(param_1);
    }



}
