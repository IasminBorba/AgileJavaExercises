package pieces;


public class QueenPieceTest extends PieceTest{
    protected Piece createQuennPiece(Piece.Color color, Piece.Type type) {
        return QueenPiece.create(color, type);
    }

    public void testCreate() {
        verifyCreation(Piece.createWhitePiece(Piece.Type.QUEEN), Piece.createBlackPiece(Piece.Type.QUEEN), Piece.Type.QUEEN, Piece.Type.QUEEN.getRepresentation());
    }
}

