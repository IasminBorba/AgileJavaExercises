package pieces;

import static pieces.King.*;

public class KingTest extends PieceTest{
    public void testCreate() {
        Piece kingBlack = createPiece(Color.BLACK, Class);
        assertEquals(King.Class, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createPiece(Color.WHITE,  Class);
        assertEquals(King.Class, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return King.create(color, null);
    }
}

