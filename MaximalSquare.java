package practice;

public class MaximalSquare {

/*

1. Let's consider the base case that the size of matrix is 0 . So return 0.
2. We'll have vars res, n and m
3. Now the dp memorization matrix that we will be making needs to have 1 extra row and column for the purpose of computing dp of first row and column .
4. Now we need to iterate the whole matrix and once we find a 1 we need to check the surrounding i.e left, uppper and upper left neighbours -> (dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
5. After we get the minimum value from neighbours we will be adding 1. Why? For two reasons:
a. If the surrounding 1's are becoming part of our maximal square then we need to increase the maximal size by 1.
b. If not then atleast we have our original 1x1 square matrix .
8. Now just find keep track of max from our dp matrix and return.

*/


    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];

        int res = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == '1'){
                    // min of previous neighbours, add 1 to measure side size.
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                    // keep track of max
                    res = Math.max(res, dp[i+1][j+1]);
                }
            }
        }
        // return size of square
        return res * res;
    }
}
