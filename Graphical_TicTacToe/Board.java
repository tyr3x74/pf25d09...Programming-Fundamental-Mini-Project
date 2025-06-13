public class Board {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public Cell[][] cells;

    public Board() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void init() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col].content = Seed.EMPTY;
            }
        }
    }

    public boolean hasWon(Seed player, int row, int col) {
        return (cells[row][0].content == player && cells[row][1].content == player && cells[row][2].content == player)
                || (cells[0][col].content == player && cells[1][col].content == player && cells[2][col].content == player)
                || (row == col && cells[0][0].content == player && cells[1][1].content == player && cells[2][2].content == player)
                || (row + col == 2 && cells[0][2].content == player && cells[1][1].content == player && cells[2][0].content == player);
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].content == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
