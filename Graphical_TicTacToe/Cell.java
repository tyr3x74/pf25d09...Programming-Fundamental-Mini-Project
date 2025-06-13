public class Cell {
    public int row, col;
    public Seed content;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = Seed.EMPTY;
    }
}
