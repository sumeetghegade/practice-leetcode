package practice;


/*
INTUITION:
Divide the matrix into regions.
The sum of a regions will be
sum(0,0 to lower right) - sum(0,0 to upper right) - sum(0,0 to lower left) + sum(0,0 to upper left)

Precompute sum in a different matrix
We'll add an extra row and column at the start to avoid out of bounds checks
 */
public class RangeSumQuery2DImmutable {

    int[][] sum;
    public RangeSumQuery2DImmutable(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        sum = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                sum[i][j] = matrix[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return(sum[row2 + 1][col2+1] - sum[row1][col2+1] - sum[row2 + 1][col1] + sum[row1][col1]);
    }
}

