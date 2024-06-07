package pieces;

public class KnightPiece extends Piece {
    public static KnightPiece create(Color color) {
        Type type = Type.KNIGHT;
        return new KnightPiece(color, type);
    }

    protected KnightPiece(Color color, Type type) {
        super(color, type);
    }
}