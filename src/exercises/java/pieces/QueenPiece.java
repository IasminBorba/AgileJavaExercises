package pieces;

public class QueenPiece extends Piece {
    public static Type type = Type.QUEEN;

    private QueenPiece(Color color, Type type) {
        super(color, type);
    }

    public static QueenPiece create(Color color) {
        return new QueenPiece(color, type);
    }

}
