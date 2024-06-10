package pieces;

import static pieces.King.*;

public class KingTest extends PieceTest{
    public void testCreate() {
        Piece kingBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.KING, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createPiece(Color.WHITE,  Class);
        assertEquals(Type.KING, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return King.create(color, null);
    }
}

