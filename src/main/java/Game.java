public class Game {
    private final boolean validMove;
    private Move lastMove = Move.NONE;
    private Board board = new Board();

    public Game() {
        this.validMove = true;
    }

    private Game(Board board, boolean validMove, Move lastMove) {
        this.board = board;
        this.validMove = validMove;
        this.lastMove = lastMove;
    }

    public Game play(Position position) {
        boolean validMove = board.add(position, lastMove.nextOne());
        if (validMove) {
            lastMove = lastMove.nextOne();
        }
        return new Game(board, validMove, lastMove);
    }

    public Move lastMove() {
        return lastMove;
    }

    public boolean invalidMove() {
        return !validMove;
    }

    public boolean isDraw() {
        return board.isFilled() && board.winningMove() == Move.NONE;
    }

    public boolean xWon() {
        return board.winningMove() == Move.X;
    }
}
