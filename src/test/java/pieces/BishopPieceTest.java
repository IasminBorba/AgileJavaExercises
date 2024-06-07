package pieces;

import static pieces.BishopPiece.*;
import static pieces.QueenPiece.Class;

public class BishopPieceTest extends PieceTest{
    public void testCreate() {
        Piece bishopBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.BISHOP, bishopBlack.getType());
        assertEquals('B', bishopBlack.getRepresentation());

        Piece bishopWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.BISHOP, bishopWhite.getType());
        assertEquals('b', bishopWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return BishopPiece.create(color);
    }
}