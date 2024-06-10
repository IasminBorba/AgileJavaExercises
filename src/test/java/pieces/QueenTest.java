package pieces;

import static pieces.Queen.*;

public class QueenTest extends PieceTest{
    public void testCreate() {
        Piece queenBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.QUEEN, queenBlack.getType());
        assertEquals('Q', queenBlack.getRepresentation());

        Piece queenWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.QUEEN, queenWhite.getType());
        assertEquals('q', queenWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Queen.create(color, null);
    }
}

