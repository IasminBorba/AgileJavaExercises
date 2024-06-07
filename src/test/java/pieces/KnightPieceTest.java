package pieces;

public class KnightPieceTest extends PieceTest{
    protected Piece createKnightPiece(Piece.Color color) {
        return KnightPiece.create(color);
    }

    public void testCreate() {
        Piece knightBlack = createKnightPiece(Piece.Color.BLACK);
        assertEquals(Piece.Type.KNIGHT, knightBlack.getType());
        assertEquals('N', knightBlack.getRepresentation());

        Piece knightWhite = createKnightPiece(Piece.Color.WHITE);
        assertEquals(Piece.Type.KNIGHT, knightWhite.getType());
        assertEquals('n', knightWhite.getRepresentation());
    }
}