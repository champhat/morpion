package morpion;

public class TicTacToe {

    private char[][] grid = new char[][]{
        {'.', '.', '.'},
        {'.', '.', '.'},
        {'.', '.', '.'}
    };

    public void processInput(Player player, int playerInput) throws TicTacToeInvalidInputException {
        final int row = (playerInput - 1) / 3;
        final int column = (playerInput - 1) % 3;
        if (grid[row][column] == '.') {
            if (player.equals(Player.FIRST)) {
                grid[row][column] = 'X';
            } else {
                grid[row][column] = 'O';
            }
        } else {
             throw new TicTacToeInvalidInputException("La case est déjà occupée");
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            boolean checkWinLine = grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][2] != '.';
            boolean checkWinColumn = grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[2][i] != '.';
            if (checkWinLine || checkWinColumn) {
                return true;
            }
        }
        boolean checkWinDiagonal1 = grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != '.';
        boolean checkWinDiagonal2 = grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != '.';
        if (checkWinDiagonal1 || checkWinDiagonal2) {
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        for (char[] line : grid) {
            for (char cell : line) {
                if (cell == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Grille du Morpion : ").append(StringConstant.LINE_SEPARATOR);
        for (char[] line : grid) {
            for (char cell : line) {
                builder.append(StringConstant.SPACE).append(cell).append(StringConstant.SPACE);
            }
            builder.append(StringConstant.LINE_SEPARATOR);
        }
        return builder.toString();
    }
}