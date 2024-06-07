package pieces;

public class BishopPiece extends Piece {
    public static Type type = Type.BISHOP;

    protected BishopPiece(Color color, Type type) {
        super(color, type);
    }

    public static BishopPiece create(Color color) {
        return new BishopPiece(color, type);
    }
}
