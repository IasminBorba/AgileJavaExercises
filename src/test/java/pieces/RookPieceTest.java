package pieces;

public class RookPieceTest extends PieceTest{
    protected Piece createRookPiece(Piece.Color color) {
        return RookPiece.create(color);
    }

    public void testCreate() {
        Piece rookBlack = createRookPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.ROOK, rookBlack.getType());
        assertEquals('R', rookBlack.getRepresentation());

        Piece rookWhite = createRookPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.ROOK, rookWhite.getType());
        assertEquals('r', rookWhite.getRepresentation());
    }
}