package pieces;

public class PawnPiece extends Piece {
    public static PawnPiece create(Color color) {
        Type type = Type.PAWN;
        return new PawnPiece(color, type);
    }

    protected PawnPiece(Color color, Type type) {
        super(color, type);
    }
}