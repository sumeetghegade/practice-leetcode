package practice;

import java.util.LinkedList;
import java.util.Queue;

/*
INTUITION:
Here we need to find the shortest path hence BFS is the best approach.
We start from 0,0 and keep traversing till we reach n-1,n-1
Keep a track of depth and return depth as answer.
[
[0,0,0],
[1,1,0],
[1,1,0]
]
 */

public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;

        // array to help us move in 8 directions [later]
        int[][] moves = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};

        // edge case: if start and end itself is not valid
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1)
            return -1;

        // pre processing for BFS
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[n][n];
        visited[0][0] = 1;
        // add starting point in queue
        queue.add(new int[]{0,0});
        int pathLength = 0;

        while(!queue.isEmpty()) {
            int qSize = queue.size();
            // process each level at a time
            for(int c = 0; c < qSize; c++) {
                int[] cur = queue.remove();
                int i = cur[0];
                int j = cur[1];
                if(i == n - 1 && j == n - 1)
                    return pathLength + 1;

                // add neighbours from 8 directions into the
                for(int[] move: moves) {
                    int new_i = i + move[0];
                    int new_j = j + move[1];
                    if(new_i >= 0 && new_i < n && new_j >= 0 && new_j < n && visited[new_i][new_j] != 1 && grid[new_i][new_j] == 0){
                        visited[new_i][new_j] = 1;
                        queue.add(new int[]{new_i, new_j});
                    }
                }
            }
            // increase count after passing a level
            pathLength++;
        }
        return -1;
    }
}
