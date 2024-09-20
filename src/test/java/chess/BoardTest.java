package chess;

import pieces.*;
import junit.framework.TestCase;

import java.util.*;
import pieces.Piece.Color;

public class BoardTest extends TestCase {
    public Board board;

    public void setUp() {
        board = new Board();
    }

    public void testBoardSizeCreated() {
        Piece[][] boardCells = board.getBoardCells();

        int boardFiles = boardCells.length;
        assertTrue(boardFiles == 8);

        int boardRows = boardCells[0].length;
        assertTrue(boardRows == 8);

        int totalBoardCells = boardFiles * boardRows;
        assertTrue(totalBoardCells == 64);
    }

    public void testBoardCreatedIsEmpty() {
        Piece[][] boardCells = board.getBoardCells();

        for (int row = 0; row < 8; row++)
            for (int file = 0; file < 8; file++)
                assertTrue(boardCells[row][file] == null);
    }

    public void testInitializeBoardToWhitePieces() {
        board.initialize();
        Piece[][] boardCells = board.getBoardCells();

        List<Piece> firstRankWhitePieces = new ArrayList<>(Arrays.asList(boardCells[0]));
        assertEqualsRow(initialWhitePieces(), firstRankWhitePieces);

        List<Piece> secondRankWhitePieces = new ArrayList<>(Arrays.asList(boardCells[1]));
        assertEqualsRow(initialWhitePawns(), secondRankWhitePieces);
    }

    public void testInitializeBoardToBlackPieces() {
        board.initialize();
        Piece[][] boardCells = board.getBoardCells();

        List<Piece> firstRankBlackPieces = new ArrayList<>(Arrays.asList(boardCells[7]));
        assertEqualsRow(initialBlackPieces(), firstRankBlackPieces);

        List<Piece> secondRankBlackPieces = new ArrayList<>(Arrays.asList(boardCells[6]));
        assertEqualsRow(initialBlackPawns(), secondRankBlackPieces);
    }

    public void testAddPieceToBoard() {
        Piece blackKing = King.createPiece(Color.BLACK);
        board.addPiece(blackKing, "a1");

        assertEquals('K', board.getPiece(0, 0).getRepresentation());
    }

    public void testNotAddPieceToBoard() {
        Piece whitePawn = Pawn.createPiece(Color.WHITE);

        try {
            board.addPiece(whitePawn, "c9");
        } catch (Exception e) {
            assertEquals("Invalid row: 9", e.getMessage());
        }
    }

    public void testRemovePieces() {
        Piece blackBishop = Bishop.createPiece(Color.BLACK);
        board.addPiece(blackBishop, "d6");

        board.removePieceFromTheBoard(blackBishop);
        assertEquals(null, board.getPiece(3, 6));
    }

    public void testMovePiece() {
        Piece whiteQueen = Queen.createPiece(Color.WHITE);
        board.addPiece(whiteQueen, "b6");

        board.movePieceToPosition("b7", whiteQueen);

        assertEquals('q', board.getPiece(6, 1).getRepresentation());
    }

    public void testGetRow() {
        board.initialize();

        List<Piece> row = board.getPiecesInRow(8);
        assertEqualsRow(initialBlackPieces(), row);
    }

    public void testGetEmptyRow() {
        board.initialize();

        List<Piece> row = board.getPiecesInRow(4);
        assertTrue(row.isEmpty());
    }

    public void testEqualsNullBoard() {
        board.initialize();

        Board boardNull = null;

        assertFalse(board.equals(boardNull));
    }

    public void testEqualsBoard() {
        board.initialize();
        assertTrue(board.equals(board));
    }

    public void testEqualsDifferentBoardsButSame() {
        board.initialize();

        Board boardClone = new Board();
        boardClone.initialize();

        assertTrue(board.equals(boardClone));
    }

    public void testEqualsNotBoard() {
        Object boardNotBoard = "";

        assertFalse(board.equals(boardNotBoard));
    }

    public void testEqualsDifferentBoards() {
        board.initialize();

        Board boardNotClone = new Board();

        assertFalse(board.equals(boardNotClone));
    }

    public void assertEqualsRow(List<Piece> expected, List<Piece> actual) {
        for (int index = 0; index < expected.size(); index++) {
            char expectedRepresentation = expected.get(index).getRepresentation();
            char actualRepresentation = actual.get(index).getRepresentation();

            assertEquals(expectedRepresentation, actualRepresentation);
        }
    }

    public List<Piece> initialWhitePieces() {
        List<Piece> firstRankWhite = new ArrayList<>();

        firstRankWhite.add(Rook.createPiece(Color.WHITE));
        firstRankWhite.add(Knight.createPiece(Color.WHITE));
        firstRankWhite.add(Bishop.createPiece(Color.WHITE));
        firstRankWhite.add(Queen.createPiece(Color.WHITE));
        firstRankWhite.add(King.createPiece(Color.WHITE));
        firstRankWhite.add(Bishop.createPiece(Color.WHITE));
        firstRankWhite.add(Knight.createPiece(Color.WHITE));
        firstRankWhite.add(Rook.createPiece(Color.WHITE));

        return firstRankWhite;
    }

    public List<Piece> initialWhitePawns() {
        List<Piece> rowWhitePawns = new ArrayList<>();

        for (int file = 0; file < 8; file++)
            rowWhitePawns.add(Pawn.createPiece(Color.WHITE));

        return rowWhitePawns;
    }

    public List<Piece> initialBlackPieces() {
        List<Piece> firstRankBlack = new ArrayList<>();

        firstRankBlack.add(Rook.createPiece(Color.BLACK));
        firstRankBlack.add(Knight.createPiece(Color.BLACK));
        firstRankBlack.add(Bishop.createPiece(Color.BLACK));
        firstRankBlack.add(Queen.createPiece(Color.BLACK));
        firstRankBlack.add(King.createPiece(Color.BLACK));
        firstRankBlack.add(Bishop.createPiece(Color.BLACK));
        firstRankBlack.add(Knight.createPiece(Color.BLACK));
        firstRankBlack.add(Rook.createPiece(Color.BLACK));

        return firstRankBlack;
    }

    public List<Piece> initialBlackPawns() {
        List<Piece> rowBlackPawns = new ArrayList<>();

        for (int file = 0; file < 8; file++)
            rowBlackPawns.add(Pawn.createPiece(Color.BLACK));

        return rowBlackPawns;
    }
}
