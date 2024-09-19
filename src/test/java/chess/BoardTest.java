package chess;

import pieces.*;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class BoardTest extends TestCase {
    public Board board;

    public void setUp() {
        board = new Board();
    }

    public void testAddPieceToBoard() {
        Piece blackKing = King.createPiece(Piece.Color.BLACK);
        board.addPiece(blackKing, "a1");

        assertEquals('K', board.getPiece(0, 0).getRepresentation());
    }

    public void testNotAddPieceInTheBoard() {
        Piece whitePawn = Pawn.createPiece(Piece.Color.WHITE);

        try {
            board.addPiece(whitePawn, "c9");
        } catch (Exception e) {
            assertEquals("Invalid row: 9", e.getMessage());
        }
    }

    public void testRemovePieces() {
        Piece blackBishop = Bishop.createPiece(Piece.Color.BLACK);
        board.addPiece(blackBishop, "d6");

        board.removePieceFromTheBoard(blackBishop);
        assertEquals(null, board.getPiece(3, 6));
    }

    public void testMovePiece() {
        Piece whiteQueen = Queen.createPiece(Piece.Color.WHITE);
        board.addPiece(whiteQueen, "b6");

        board.movePieceToPosition("b7", whiteQueen);

        assertEquals('q', board.getPiece(1, 6).getRepresentation());
    }

    public void testGetRank() {
        board.initialize();

        List<Piece> rank = board.getPiecesInRow(8);
        assertEqualsRank(initialRankWhitePieces(), rank);
    }

    public void assertEqualsRank(List<Piece> expected, List<Piece> actual) {
        for (int index = 0; index < expected.size(); index++) {
            char expectedRepresentation = expected.get(index).getRepresentation();
            char actualRepresentation = actual.get(index).getRepresentation();

            assertEquals(expectedRepresentation, actualRepresentation);
        }

    }

    public List<Piece> initialRankWhitePieces() {
        List<Piece> rank = new ArrayList<>();

        rank.add(Rook.createPiece(Piece.Color.BLACK));
        rank.add(Knight.createPiece(Piece.Color.BLACK));
        rank.add(Bishop.createPiece(Piece.Color.BLACK));
        rank.add(Queen.createPiece(Piece.Color.BLACK));
        rank.add(King.createPiece(Piece.Color.BLACK));
        rank.add(Bishop.createPiece(Piece.Color.BLACK));
        rank.add(Knight.createPiece(Piece.Color.BLACK));
        rank.add(Rook.createPiece(Piece.Color.BLACK));

        return rank;
    }
}
