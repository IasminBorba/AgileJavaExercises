package pieces;


public class KingPieceTest extends PieceTest{
    protected Piece createKingPiece(Piece.Color color, Piece.Type type) {
        return KingPiece.create(color, type);
    }

    public void testCreate() {
        verifyCreation(Piece.createWhitePiece(Piece.Type.KING), Piece.createBlackPiece(Piece.Type.KING), Piece.Type.KING, Piece.Type.KING.getRepresentation());
    }
}

