package pieces;

public class KingPiece extends Piece {
    public static KingPiece create(Color color, Type type) {
        return new KingPiece(color, type);
    }

    private KingPiece(Color color, Type type) {
        super(color, type);
    }
}
