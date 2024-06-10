package pieces;

import static pieces.Knight.*;
import static pieces.Queen.Class;

public class KnightTest extends PieceTest{
    public void testCreate() {
        Piece knightBlack = createPiece(Color.BLACK, Class);
        assertEquals(Knight.Class, knightBlack.getType());
        assertEquals('N', knightBlack.getRepresentation());

        Piece knightWhite = createPiece(Color.WHITE, Class);
        assertEquals(Knight.Class, knightWhite.getType());
        assertEquals('n', knightWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Knight.create(color, null);
    }
}