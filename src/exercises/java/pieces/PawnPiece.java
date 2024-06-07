package pieces;

public class PawnPiece extends Piece {
    public static Type type = Type.PAWN;

    protected PawnPiece(Color color, Type type) {
        super(color, type);
    }

    public static PawnPiece create(Color color) {
        return new PawnPiece(color, type);
    }
}