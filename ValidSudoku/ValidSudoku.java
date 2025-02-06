package ValidSudoku;
// Determine if a 9 x 9 Sudoku board is valid.Only the filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.Each column must contain the digits 1-9 without repetition.Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.Note:

// A Sudoku board(partially filled)could be valid but is not necessarily solvable.Only the filled cells need to be validated according to the mentioned rules.

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] subgrids = new boolean[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                char currentChar = board[i][j];
                if (currentChar == '.') {
                    continue;
                }
                int number = currentChar - '0' - 1;
                int subgridIndex = (i / 3) * 3 + j / 3;
                if (rows[i][number] || columns[j][number] || subgrids[subgridIndex][number]) {
                    // If any is true, then the board is not valid.
                    return false;
                }
                rows[i][number] = true;
                columns[j][number] = true;
                subgrids[subgridIndex][number] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
