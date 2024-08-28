package pieces;

import static pieces.Queen.Class;
import static pieces.Rook.*;

public class RookTest extends PieceTest {
    public void testCreate() {
        Piece rookBlack = createPiece(Color.BLACK, Class);
        assertEquals(Rook.Class, rookBlack.getType());
        assertEquals('R', rookBlack.getRepresentation());

        Piece rookWhite = createPiece(Color.WHITE, Class);
        assertEquals(Rook.Class, rookWhite.getType());
        assertEquals('r', rookWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Rook.create(color, null);
    }
}