public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isWithin(int maxRow, int maxColumn) {
        return row >= 0 && row < maxRow && column >= 0 && column < maxColumn;
    }



    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
