package pieces;

public class KingPieceTest extends PieceTest{
    protected Piece createKingPiece(Piece.Color color) {
        return KingPiece.create(color);
    }

    public void testCreate() {
        Piece kingBlack = createKingPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.KING, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createKingPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.KING, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }
}

