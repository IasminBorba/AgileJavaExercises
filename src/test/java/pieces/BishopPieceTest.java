package pieces;

public class BishopPieceTest extends PieceTest{
    protected Piece createBishopPiece(Piece.Color color) {
        return BishopPiece.create(color);
    }

    public void testCreate() {
        Piece bishopBlack = createBishopPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.BISHOP, bishopBlack.getType());
        assertEquals('B', bishopBlack.getRepresentation());

        Piece bishopWhite = createBishopPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.BISHOP, bishopWhite.getType());
        assertEquals('b', bishopWhite.getRepresentation());
    }
}