package pieces;

import static pieces.PawnPiece.*;
import static pieces.QueenPiece.Class;

public class PawnPieceTest extends PieceTest{
    public void testCreate() {
        Piece pawnBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.PAWN, pawnBlack.getType());
        assertEquals('P', pawnBlack.getRepresentation());

        Piece pawnWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.PAWN, pawnWhite.getType());
        assertEquals('p', pawnWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return PawnPiece.create(color);
    }
}