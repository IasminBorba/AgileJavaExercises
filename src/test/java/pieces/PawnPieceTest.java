package pieces;

import static pieces.PawnPiece.*;

public class PawnPieceTest extends PieceTest{
    public void testCreate() {
        Piece pawnBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.PAWN, pawnBlack.getType());
        assertEquals('P', pawnBlack.getRepresentation());

        Piece pawnWhite = createPiece(Color.WHITE, type);
        assertEquals(Type.PAWN, pawnWhite.getType());
        assertEquals('p', pawnWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return PawnPiece.create(color);
    }
}