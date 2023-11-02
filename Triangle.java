package practice;

import java.util.List;

/*
At any point we have 2 choices:
Either choose left element or right element:

1
2 3
4 5 6
7 8 9 1

   1
  2 3
 4 5 6
7 8 9 1

So we can take a top down DP approach which essentially will translate to DFS.
We can recursively keep making the choice between the two elements.
Once the we reach the last row we return the element itself and then as our result propogates
back up the triangle we select the one that gives us the min sum.

Also we will be doing repetitive work here and the fact that there will be only one min sum from any element
we can store min sum once it is calculated at any point and then use that for later calls instead of going down the trianle
again for that element.

TC:
n^2 -> we have n^2/2 states so there will be these many calls.
same for time complexity which will be n^2(memo) + n(stack)



 */

public class Triangle {

    // Method 1:
    // top down memo
    public int minimumTotal1(List<List<Integer>> triangle) {
        Integer[][] memo = new Integer[triangle.size()][triangle.size()];
        return helper(triangle, 0, 0, memo);
    }

    public int helper(List<List<Integer>> triangle, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null)
            return memo[i][j];
        if (i == triangle.size() - 1)
            return triangle.get(i).get(j);
        int res = 0;
        res += Math.min(triangle.get(i).get(j) + helper(triangle, i + 1, j, memo), triangle.get(i).get(j) + helper(triangle, i + 1, j + 1, memo));
        memo[i][j] = res;
        return res;
    }

    // Method 2:
    // bottom up dp
    public int minimumTotal2(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];

    }
}
