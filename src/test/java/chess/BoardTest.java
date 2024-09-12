package chess;

import pieces.*;
import junit.framework.TestCase;

public class BoardTest extends TestCase {
    public Board board;

    public void setUp() {
        board = new Board();
    }

    public void testAddPieceToBoard() {
        Piece blackKing = King.create(Piece.Color.BLACK);
        board.addPiece(blackKing, "a1");

        assertEquals('K', board.getPiece(0, 0).getRepresentation());
    }

    public void testNotAddPieceInTheBoard() {
        Piece whitePawn = Pawn.create(Piece.Color.WHITE);

        try {
            board.addPiece(whitePawn, "c9");
        } catch (Exception e) {
            assertEquals("Invalid rank: 9", e.getMessage());
        }
    }

    public void testRemovePieces() {
        Piece blackBishop = Bishop.create(Piece.Color.BLACK);
        board.addPiece(blackBishop, "d6");

        board.removePieceFromTheBoard(blackBishop);
        assertEquals(null, board.getPiece(3, 6));
    }

    public void testMovePiece() {
        Piece whiteQueen = Queen.create(Piece.Color.WHITE);
        board.addPiece(whiteQueen, "b6");

        board.movePiece("b7", whiteQueen);

        assertEquals('q', board.getPiece(1, 6).getRepresentation());
    }
}
