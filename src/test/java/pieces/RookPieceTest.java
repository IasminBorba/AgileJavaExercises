package pieces;

import static pieces.RookPiece.*;

public class RookPieceTest extends PieceTest{
    public void testCreate() {
        Piece rookBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.ROOK, rookBlack.getType());
        assertEquals('R', rookBlack.getRepresentation());

        Piece rookWhite = createPiece(Color.WHITE, type);
        assertEquals(Type.ROOK, rookWhite.getType());
        assertEquals('r', rookWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return RookPiece.create(color);
    }
}