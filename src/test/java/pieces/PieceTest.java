package pieces;

import junit.framework.TestCase;

public class PieceTest extends TestCase {
    public void testCreate() {
        verifyCreation(Piece.createWhitePiece(Piece.Type.PAWN), Piece.createBlackPiece(Piece.Type.PAWN), Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);
        verifyCreation(Piece.createWhitePiece(Piece.Type.ROOK), Piece.createBlackPiece(Piece.Type.ROOK), Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);
        verifyCreation(Piece.createWhitePiece(Piece.Type.KNIGHT), Piece.createBlackPiece(Piece.Type.KNIGHT), Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION);
        verifyCreation(Piece.createWhitePiece(Piece.Type.BISHOP), Piece.createBlackPiece(Piece.Type.BISHOP),Piece.Type.BISHOP, Piece.BISHOP_REPRESENTATION);
        verifyCreation(Piece.createWhitePiece(Piece.Type.QUEEN), Piece.createBlackPiece(Piece.Type.QUEEN), Piece.Type.QUEEN, Piece.QUEEN_REPRESENTATION);
        verifyCreation(Piece.createWhitePiece(Piece.Type.KING), Piece.createBlackPiece(Piece.Type.KING), Piece.Type.KING, Piece.KING_REPRESENTATION);

        Piece blank = Piece.noPiece();
        assertEquals('.', blank.getRepresentation());
        assertEquals(Piece.Type.NO_PIECE, blank.getType());
        assertEquals(0, blank.getStrength(blank));
    }

    private void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(Character.toLowerCase(representation), whitePiece.getRepresentation());
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(representation, blackPiece.getRepresentation());
    }
}