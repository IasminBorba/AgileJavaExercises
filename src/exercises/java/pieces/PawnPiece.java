package pieces;

public class PawnPiece extends Piece {
    public static PawnPiece create(Color color, Type type) {
        return new PawnPiece(color, type);
    }

    private PawnPiece(Color color, Type type) {
        super(color, type);
    }
}
