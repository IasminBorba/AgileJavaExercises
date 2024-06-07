package pieces;

import static pieces.QueenPiece.*;

public class QueenPieceTest extends PieceTest{
    public void testCreate() {
        Piece queenBlack = createPiece(Color.BLACK, type);
        assertEquals(Type.QUEEN, queenBlack.getType());
        assertEquals('Q', queenBlack.getRepresentation());

        Piece queenWhite = createPiece(Color.WHITE, type);
        assertEquals(Type.QUEEN, queenWhite.getType());
        assertEquals('q', queenWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return QueenPiece.create(color);
    }
}

