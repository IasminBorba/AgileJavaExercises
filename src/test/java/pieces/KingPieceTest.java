package pieces;

import static pieces.KingPiece.*;

public class KingPieceTest extends PieceTest{
    public void testCreate() {
        Piece kingBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.KING, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createPiece(Color.WHITE,  type);
        assertEquals(Type.KING, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return KingPiece.create(color);
    }
}

