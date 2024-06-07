package pieces;

import static pieces.KnightPiece.*;
import static pieces.QueenPiece.Class;

public class KnightPieceTest extends PieceTest{
    public void testCreate() {
        Piece knightBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.KNIGHT, knightBlack.getType());
        assertEquals('N', knightBlack.getRepresentation());

        Piece knightWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.KNIGHT, knightWhite.getType());
        assertEquals('n', knightWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return KnightPiece.create(color);
    }
}