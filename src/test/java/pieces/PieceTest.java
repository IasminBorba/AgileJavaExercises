package pieces;

import junit.framework.TestCase;


abstract public class PieceTest extends TestCase {
    private Piece piece;

    public void setUp() {
        piece = createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
    }

    abstract protected Piece createPiece(Piece.Color color, Piece.Type type);

    public void testCreatePiece() {
        assertEquals('R', piece.getRepresentation());
        assertEquals(Piece.Type.ROOK, piece.getType());
        assertEquals(0.0, piece.getPoints());

        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.BISHOP), createPiece(Piece.Color.BLACK, Piece.Type.BISHOP), Piece.Type.BISHOP, Piece.Type.BISHOP.getRepresentation());
        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.KING), createPiece(Piece.Color.BLACK, Piece.Type.KING), Piece.Type.KING, Piece.Type.KING.getRepresentation());
        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT), createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT), Piece.Type.KNIGHT, Piece.Type.KNIGHT.getRepresentation());
        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.PAWN), createPiece(Piece.Color.BLACK, Piece.Type.PAWN), Piece.Type.PAWN, Piece.Type.PAWN.getRepresentation());
        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.QUEEN), createPiece(Piece.Color.BLACK, Piece.Type.QUEEN), Piece.Type.QUEEN, Piece.Type.QUEEN.getRepresentation());
        verifyCreation(createPiece(Piece.Color.WHITE, Piece.Type.ROOK), createPiece(Piece.Color.BLACK, Piece.Type.ROOK), Piece.Type.ROOK, Piece.Type.ROOK.getRepresentation());


    }

    protected void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(Character.toLowerCase(type.getRepresentation()), whitePiece.getType().getRepresentation());
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(representation, blackPiece.getType().getRepresentation());
    }
}