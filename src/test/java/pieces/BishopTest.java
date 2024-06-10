package pieces;

import static pieces.Bishop.*;
import static pieces.Queen.Class;

public class BishopTest extends PieceTest{
    public void testCreate() {
        Piece bishopBlack = createPiece(Color.BLACK, Class);
        assertEquals(Bishop.Class, bishopBlack.getType());
        assertEquals('B', bishopBlack.getRepresentation());

        Piece bishopWhite = createPiece(Color.WHITE, Class);
        assertEquals(Bishop.Class, bishopWhite.getType());
        assertEquals('b', bishopWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Bishop.create(color, null);
    }
}