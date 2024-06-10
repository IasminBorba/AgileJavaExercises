package pieces;

import static pieces.Pawn.*;
import static pieces.Queen.Class;

public class PawnTest extends PieceTest{
    public void testCreate() {
        Piece pawnBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.PAWN, pawnBlack.getType());
        assertEquals('P', pawnBlack.getRepresentation());

        Piece pawnWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.PAWN, pawnWhite.getType());
        assertEquals('p', pawnWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Pawn.create(color, null);
    }
}