package pieces;

public class RookPiece extends Piece {
    public static Type type = Type.ROOK;

    protected RookPiece(Color color, Type type) {
        super(color, type);
    }

    public static RookPiece create(Color color) {
        return new RookPiece(color, type);
    }
}