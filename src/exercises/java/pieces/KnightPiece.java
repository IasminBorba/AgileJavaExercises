package pieces;

public class KnightPiece extends Piece {
    public static Type type = Type.KNIGHT;

    protected KnightPiece(Color color, Type type) {
        super(color, type);
    }

    public static KnightPiece create(Color color) {
        return new KnightPiece(color, type);
    }
}