package pieces;

import junit.framework.TestCase;

public class PieceTest extends TestCase {
    public void testCreate() {
    verifyCreation(Piece.createWhitePiece(Piece.Type.PAWN), Piece.createBlackPiece(Piece.Type.PAWN), Piece.Type.PAWN, Piece.Type.PAWN.getRepresentation());
    verifyCreation(Piece.createWhitePiece(Piece.Type.ROOK), Piece.createBlackPiece(Piece.Type.ROOK), Piece.Type.ROOK, Piece.Type.ROOK.getRepresentation());
    verifyCreation(Piece.createWhitePiece(Piece.Type.KNIGHT), Piece.createBlackPiece(Piece.Type.KNIGHT), Piece.Type.KNIGHT, Piece.Type.KNIGHT.getRepresentation());
    verifyCreation(Piece.createWhitePiece(Piece.Type.BISHOP), Piece.createBlackPiece(Piece.Type.BISHOP),Piece.Type.BISHOP, Piece.Type.BISHOP.getRepresentation());

//    Piece blank = Piece.noPiece();
//    assertEquals('.', blank.getRepresentation());
//    assertEquals(Piece.Type.NO_PIECE, blank.getType());
//    assertEquals(0.0, blank.getPoints());
    }

    protected void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(Character.toLowerCase(type.getRepresentation()), whitePiece.getRepresentation());
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(representation, blackPiece.getRepresentation());
    }

}