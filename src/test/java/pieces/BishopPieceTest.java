package pieces;

import static pieces.BishopPiece.*;

public class BishopPieceTest extends PieceTest{
    public void testCreate() {
        Piece bishopBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.BISHOP, bishopBlack.getType());
        assertEquals('B', bishopBlack.getRepresentation());

        Piece bishopWhite = createPiece(Color.WHITE, type);
        assertEquals(Type.BISHOP, bishopWhite.getType());
        assertEquals('b', bishopWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return BishopPiece.create(color);
    }
}