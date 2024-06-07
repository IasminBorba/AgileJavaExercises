package pieces;

public class RookPiece extends Piece {
    public static RookPiece create(Color color) {
        Type type = Type.ROOK;
        return new RookPiece(color, type);
    }

    protected RookPiece(Color color, Type type) {
        super(color, type);
    }
}