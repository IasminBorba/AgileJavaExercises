package pieces;

public class BishopPiece extends Piece {
    public static BishopPiece create(Color color) {
        Type type = Type.BISHOP;
        return new BishopPiece(color, type);
    }

    protected BishopPiece(Color color, Type type) {
        super(color, type);
    }
}
