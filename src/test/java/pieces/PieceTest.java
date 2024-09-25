package pieces;

import chess.Position;
import junit.framework.TestCase;


abstract public class PieceTest extends TestCase {
    private Piece piece;

    public void setUp() {
        piece = createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
    }

    abstract protected Piece createPiece(Piece.Color color, Piece.Type type);

    public void testCreatePiece() {
        assertEquals(Piece.Type.ROOK, piece.getType());
        assertTrue(piece.isWhite());
        assertEquals(Piece.Type.ROOK.getRepresentation(), piece.getRepresentation());
        assertEquals(0.0, piece.getPoints());
        assertEquals(null, piece.getImage());

        verifyGeType(createPiece(Piece.Color.WHITE, Piece.Type.BISHOP), Piece.Type.BISHOP);
        verifyGeColor(createPiece(Piece.Color.BLACK, Piece.Type.KING), Piece.Color.BLACK);
        verifyGeRepresentation(createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT), Piece.Type.KNIGHT.getRepresentation());
        verifyGePoints(createPiece(Piece.Color.BLACK, Piece.Type.PAWN), 1.0);
        verifyUpdatePoints(createPiece(Piece.Color.WHITE, Piece.Type.ROOK), 5.0);
        verifyGePosition(createPiece(Piece.Color.BLACK, Piece.Type.QUEEN));
        verifySetPosition(createPiece(Piece.Color.WHITE, Piece.Type.ROOK), new Position(5,5));
        verifyIsWhite(createPiece(Piece.Color.WHITE, Piece.Type.ROOK));
        verifyIsBlack(createPiece(Piece.Color.BLACK, Piece.Type.ROOK));
        verifyCompareTo(createPiece(Piece.Color.WHITE, Piece.Type.ROOK), createPiece(Piece.Color.WHITE, Piece.Type.ROOK));
        verifyNotCompareTo(createPiece(Piece.Color.BLACK, Piece.Type.ROOK), createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
    }

    protected void verifyGeType(Piece piece, Piece.Type type) {
        assertEquals(type, piece.getType());
    }

    protected void verifyGeColor(Piece piece, Piece.Color color) {
        assertEquals(color, piece.getColor());
    }

    protected void verifyGeRepresentation(Piece piece, char representation) {
        assertEquals(representation, piece.getRepresentation());
    }

    protected void verifyGePoints(Piece piece, double points) {
        assertEquals(points, piece.getPoints());
    }

    protected void verifyUpdatePoints(Piece piece, double points) {
        piece.updatePoints(points);
        assertEquals(points, piece.getPoints());
    }

    protected void verifyGePosition(Piece piece) {
        assertTrue(piece.getPosition() == null);
    }

    protected void verifySetPosition(Piece piece, Position position) {
        piece.setPosition(position);
        assertEquals(position, piece.getPosition());
    }

    protected void verifyIsWhite(Piece piece) {
        assertTrue(piece.isWhite());
    }

    protected void verifyIsBlack(Piece piece) {
        assertTrue(piece.isBlack());
    }

    protected void verifyCompareTo(Piece piece, Piece otherPiece) {
        assertTrue(piece.compareTo(otherPiece));
    }

    protected void verifyNotCompareTo(Piece piece, Piece otherPiece) {
        assertFalse(piece.compareTo(otherPiece));
    }
}
