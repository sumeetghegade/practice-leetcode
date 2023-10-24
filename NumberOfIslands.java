package practice;


/*
Simple DFS:
start at each node where val == 1
if not visited --> count++
perform dfs on node
return count
O(nm)
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {

        int[][] visited = new int[grid.length][grid[0].length];
        int count = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(visited[i][j] != 1 && grid[i][j] == '1') {
                    count++;
                    dfs(grid, visited,  i,  j);
                }
            }
        }

        return count;
    }


    public void dfs(char[][] grid, int[][] visited, int i, int j) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || visited[i][j] == 1 || grid[i][j] == '0')
            return;

        visited[i][j] = 1;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j + 1);
        dfs(grid, visited, i, j - 1);
    }
}
