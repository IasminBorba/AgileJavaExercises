package pieces;

public class PawnPieceTest extends PieceTest{
    protected Piece createPawnPiece(Piece.Color color) {
        return PawnPiece.create(color);
    }

    public void testCreate() {
        Piece pawnBlack = createPawnPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.PAWN, pawnBlack.getType());
        assertEquals('P', pawnBlack.getRepresentation());

        Piece pawnWhite = createPawnPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.PAWN, pawnWhite.getType());
        assertEquals('p', pawnWhite.getRepresentation());
    }
}