
public enum Move {
    NONE,
    X,
    O;

    public Move nextOne() {
        if (this == Move.NONE || this == Move.O)
            return Move.X;
        return Move.O;
    }
}
