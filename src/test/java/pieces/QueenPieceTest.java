package pieces;

public class QueenPieceTest extends PieceTest{
    protected Piece createQuennPiece(Piece.Color color) {
        return QueenPiece.create(color);
    }

    public void testCreate() {
//        verifyCreation(Piece.createWhitePiece(Piece.Type.QUEEN), Piece.createBlackPiece(Piece.Type.QUEEN), Piece.Type.QUEEN, Piece.Type.QUEEN.getRepresentation());

        Piece queenBlack = createQuennPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.QUEEN, queenBlack.getType());
        assertEquals('Q', queenBlack.getRepresentation());


        Piece queenWhite = createQuennPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.QUEEN, queenWhite.getType());
        assertEquals('q', queenWhite.getRepresentation());
    }
}

