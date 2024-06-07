package pieces;

import static pieces.KnightPiece.*;

public class KnightPieceTest extends PieceTest{
    public void testCreate() {
        Piece knightBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.KNIGHT, knightBlack.getType());
        assertEquals('N', knightBlack.getRepresentation());

        Piece knightWhite = createPiece(Color.WHITE, type);
        assertEquals(Type.KNIGHT, knightWhite.getType());
        assertEquals('n', knightWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return KnightPiece.create(color);
    }
}