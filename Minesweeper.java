package practice;


/*
Simple DFS solution.
NOT HARD

Get the requirements clarified.
 */
public class Minesweeper {

    // Predefine an array to make traversing adjacent matrices easier
    int[][] moves = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {

        int row = click[0];
        int col = click[1];

        // if mine --> end game
        if(board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }
        // else start traversing board
        else {
            dfs(board, row, col);
        }
        return board;
    }


    public void dfs(char[][] board, int row, int col) {

        // return if out of bounds of if we find anything except and empty tile
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'E')
            return;

        // call function to calculate adjacent mines
        int numMines = countAdjacentMines(board, row, col);

        // if no mines then start exploring all adjacent cells. perform dfs on all
        if(numMines == 0) {
            board[row][col] = 'B';
            for(int[] move: moves) {
                int i = row + move[0];
                int j = col + move[1];
                dfs(board, i, j);
            }
        }
        // else mark the number of mines
        else {
            board[row][col] = (char) ('0' + numMines);
        }
    }

    public int countAdjacentMines(char[][] board, int row, int col) {
        int numMines = 0;

        // count mines in adjacent cells
        for(int move[]: moves) {
            int i = row + move[0];
            int j = col + move[1];
            if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 'M') {
                numMines++;
            }
        }
        return numMines;
    }
}
