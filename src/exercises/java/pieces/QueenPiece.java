package pieces;

public class QueenPiece extends Piece {
    public static QueenPiece create(Color color) {
        Type type = Type.QUEEN;
        return new QueenPiece(color, type);
    }

    private QueenPiece(Color color, Type type) {
        super(color, type);
    }

}
