import java.util.*;

public class Board {
    Move[][] board = new Move[3][3];

    public boolean add(Position position, Move move) {
        if (!position.isWithin(3, 3))
            throw new IllegalArgumentException();

        if (getMoveAt(position) == null) {
            setMoveAt(position, move);
            return true;
        }

        return false;
    }

    public boolean isFilled() {
        ArrayList<Move> moves = new ArrayList<>();
        Arrays.stream(board).forEach(row -> moves.addAll(Arrays.asList(row)));
        return moves.stream().filter(Objects::nonNull).count() >= 9;
    }

    public Move winningMove() {
        Set<Position[]> winningConfigs = allWinningConfigs();
        Optional<Position[]> winner = winningConfigs.stream().filter(this::allElementsAreEqualAt).findFirst();

        if (winner.isPresent()) {
            Position samplePosition = winner.get()[0];
            return getMoveAt(samplePosition);
        }

        return Move.NONE;
    }

    private boolean allElementsAreEqualAt(Position... positions) {
        if (positions.length == 0)
            return false;

        Move first = getMoveAt(positions[0]);
        return Arrays.stream(positions).filter(p -> !(first.equals(getMoveAt(p)))).count() == 0;
    }

    private Move getMoveAt(Position p) {
        return board[p.getRow()][p.getColumn()];
    }

    private void setMoveAt(Position p, Move m) {
        board[p.getRow()][p.getColumn()] = m;
    }

    private Set<Position[]> allWinningConfigs() {
        Set<Position[]> winningConfigs = new HashSet<>();
        winningConfigs.addAll(allColumns());
        winningConfigs.addAll(allRows());
        winningConfigs.addAll(diagonals());
        return winningConfigs;
    }

    private Set<Position[]> diagonals() {
        Set<Position[]> diagonals = new HashSet<>();
        diagonals.add(new Position[] {new Position(0,0), new Position(1,1), new Position(2,2)});
        diagonals.add(new Position[] {new Position(0,2), new Position(1,1), new Position(2,0)});
        return diagonals;
    }

    private Set<Position[]> allRows() {
        Set<Position[]> rows = new HashSet<>();
        for (int i = 0; i < 3; ++i) {
            rows.add(new Position[]{
                    new Position(i, 0),
                    new Position(i, 1),
                    new Position(i, 2)
            });
        }
        return rows;
    }

    private Set<Position[]> allColumns() {
        Set<Position[]> columns = new HashSet<>();
        for (int i = 0; i < 3; ++i) {
            columns.add(new Position[]{
                    new Position(0, i),
                    new Position(1, i),
                    new Position(2, i)
            });
        }
        return columns;
    }
}
