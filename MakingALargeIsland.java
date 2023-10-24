package practice;

import java.util.HashMap;
import java.util.HashSet;


/*
INTUITION:
Issue: There might be some zero which on turning into 1 will connect two adjacent islands

very first approach:
1. We can use dfs implement an algorithm to get the area of any island in the matrix.
2. We can replace a zero with 1 and get that islands area. Do this for all zeros.

This is not the best approach. Doing this we will be revisiting the same islands multiple times. Redundant.
Can do better.

Store size?

1. We perform DFS on all islands. When traversing we mark that island. Use a unique number to mark it.
2. While performing dfs we count number of visited nodes giving us the area. Store this (islandIndex : Area) values in a Map.
3. Now we go to each zero and check its adjacent islands. Add their areas and get the area of island if that zero turned into a 1.
4. Return max area after going to all zeroes.

We visit each node only once. O(m*n)

 */

public class MakingALargeIsland {

    HashMap<Integer, Integer> islandArea;
    public int largestIsland(int[][] grid) {
        // Make global, we will need this in other methods
        islandArea = new HashMap<>();
        islandArea.put(0,0);

        // Start here
        int res = -1;
        int n = grid.length;
        int m = grid[0].length;
        // 0 and 1 already taken. Let's name islands starting from 2
        int index = 2;

        // Calculate area of each island
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    // add to area map
                    islandArea.put(index, calculateArea(grid, i, j, index++));
                }
            }
        }

        // travers all zeroes
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0){
                    // calculate area if zero turned to 1
                    // Actual area of island will be curArea + 1. but doesn't matter. if area is to be returned then add 1 before returning
                    int curArea = getAdjacentIslandsArea(grid, i,  j);
                    // retain max value
                    res = Math.max(res, curArea);
                }
            }
        }

        // edge case: what is no zeroes. return size of matrix
        return res == -1 ? n*m : res + 1 ;
    }

    public int getAdjacentIslandsArea(int[][] grid, int i, int j) {
        // the zero might be surrounded by same island on multiple sides
        // use a set to avoid adding area multiple times
        HashSet<Integer> set = new HashSet<>();
        int area = 0;
        if(i > 0) {
            set.add(grid[i-1][j]);
            area += islandArea.get(grid[i-1][j]);
        }
        if(i < grid.length - 1 && !set.contains(grid[i+1][j])) {
            set.add(grid[i+1][j]);
            area += islandArea.get(grid[i+1][j]);
        }
        if(j > 0 && !set.contains(grid[i][j-1])) {
            set.add(grid[i][j-1]);
            area += islandArea.get(grid[i][j-1]);
        }
        if(j < grid[0].length - 1 && !set.contains(grid[i][j+1])) {
            set.add(grid[i][j+1]);
            area += islandArea.get(grid[i][j+1]);
        }

        return area;
    }

    // recursive dfs call
    public int calculateArea(int[][] grid, int i, int j, int index) {
        int area = 0;
        if(i < 0 || i >= grid.length || j < 0 || j > grid[0].length - 1 || grid[i][j] != 1)
            return 0;

        grid[i][j] = index;
        area += calculateArea(grid, i+1, j, index);
        area += calculateArea(grid, i-1, j, index);
        area += calculateArea(grid, i, j+1, index);
        area += calculateArea(grid, i, j-1, index);
        return area + 1;
    }
}
