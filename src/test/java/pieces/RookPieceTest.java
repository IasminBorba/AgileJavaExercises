package pieces;

import static pieces.QueenPiece.Class;
import static pieces.RookPiece.*;

public class RookPieceTest extends PieceTest{
    public void testCreate() {
        Piece rookBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.ROOK, rookBlack.getType());
        assertEquals('R', rookBlack.getRepresentation());

        Piece rookWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.ROOK, rookWhite.getType());
        assertEquals('r', rookWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return RookPiece.create(color);
    }
}