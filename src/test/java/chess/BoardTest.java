package chess;

import pieces.*;
import util.StringUtil;
import junit.framework.TestCase;

import java.io.*;
import java.util.*;

public class BoardTest extends TestCase {
    public Board board;

    public void setUp() {
        board = new Board();
    }

    public void testCreate() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        assertEquals(16, board.getPiecesWhite());
        assertEquals(16, board.getPiecesBlack());

        String firstRank = board.getRank(8);
        assertEquals("RNBQKBNR", firstRank);

        String secondRank = board.getRank(7);
        assertEquals("PPPPPPPP", secondRank);

        String emptyRank = board.getRank(4);
        assertEquals("........", emptyRank);

        String seventhRank = board.getRank(2);
        assertEquals("pppppppp", seventhRank);


        String eighthRank = board.getRank(1);
        assertEquals("rnbqkbnr", eighthRank);

        char pieceA8 = board.getPieceRepresentation('a', 8);
        assertEquals('R', pieceA8);

        char pieceE1 = board.getPieceRepresentation('e', 1);
        assertEquals('k', pieceE1);

        assertEquals(startingBoard(), board.print());
    }

    public void testCreateBoard() {
        board.createBoard();

        assertEquals(0, board.pieceCount());
        assertEquals(0, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                board.print()
        );

        Piece blackKing = King.create(Piece.Color.BLACK);
        board.addPiece(blackKing, 'b', 6);
        assertEquals('K', board.getPieceRepresentation('b', 6));


        Piece blackRook = Rook.create(Piece.Color.BLACK);
        board.addPiece(blackRook, 'b', 5);
        assertEquals('R', board.getPieceRepresentation('b', 5));

        Piece whiteKing = King.create(Piece.Color.WHITE);
        board.addPiece(whiteKing, 'c', 4);
        assertEquals('k', board.getPieceRepresentation('c', 4));

        assertEquals(
                blankRank + blankRank +
                        StringUtil.appendNewLine(".K......") +
                        StringUtil.appendNewLine(".R......") +
                        StringUtil.appendNewLine("..k.....") +
                        blankRank + blankRank + blankRank,
                board.print()
        );
    }

    public void testFileBoard() throws IOException {
        board.initialize();
        File fileBoard = new File("testBoard.txt");
        board.insertFile(fileBoard);

        board.writePiecesInFile();
        assertTrue(fileBoard.exists() && board.file.exists());

        assertEquals(startingBoard(), board.readFileBoard());
        assertEquals(board.print(), board.readFileBoard());

        board.addPiece(King.create(Piece.Color.BLACK), 'd', 6);
        board.writePiecesInFile();

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        StringUtil.appendNewLine("...K....") +
                        blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.readFileBoard()
        );
        assertEquals(board.print(), board.readFileBoard());


        board.deleteFile();
        assertFalse(board.file.exists());
        assertFalse(fileBoard.exists());

        try {
            board.writePiecesInFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            board.readFileBoard();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testFileBoardObj() throws IOException, ClassNotFoundException {
        board.initialize();
        String filename = "testBoardObj.txt";
        File fileBoard = new File(filename);
        board.insertFile(fileBoard);

        board.writeFileBoardObj();
        assertTrue(fileBoard.exists() && board.file.exists());
        assertTrue(board.equals(board.readFileBoardObj()));

        assertEquals(startingBoard(), board.readFileBoardObj().print());

        board.addPiece(King.create(Piece.Color.BLACK), 'd', 6);
        board.writeFileBoardObj();

        String blankRank = StringUtil.appendNewLine("........");
        assertTrue(board.equals(board.readFileBoardObj()));
        assertEquals(StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        StringUtil.appendNewLine("...K....") +
                        blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.readFileBoardObj().print()
        );

        board.deleteFile();
        assertFalse(board.file.exists());
        assertFalse(fileBoard.exists());

        try {
            board.readFileBoardObj();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            board.writeFileBoardObj();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testAnonymousInner() {
        Board board = new Board() {
            public void addPiecesOfRank(Piece.Color color) {
                int rank;
                if (Objects.equals(color, Piece.Color.WHITE)) {
                    rank = 5;
                } else {
                    rank = 2;
                }
                Piece rook = Rook.create(color);
                addPiece(rook, 6, rank);
                Piece knight = Knight.create(color);
                addPiece(knight, 7, rank);
                Piece bishop = Bishop.create(color);
                addPiece(bishop, 4, rank);
                Piece queen = Queen.create(color);
                addPiece(queen, 2, rank);
                Piece king = King.create(color);
                addPiece(king, 3, rank);
                Piece bishop2 = Bishop.create(color);
                addPiece(bishop2, 0, rank);
                Piece knight2 = Knight.create(color);
                addPiece(knight2, 1, rank);
                Piece rook2 = Rook.create(color);
                addPiece(rook2, 5, rank);
            }
        };
        board.createBoard();
        board.addPiecesOfRank(Piece.Color.WHITE);
        board.addPiecesOfRank(Piece.Color.BLACK);

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(blankRank + blankRank + blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("BBKNNQRR") +
                        StringUtil.appendNewLine("bbknnqrr"),
                board.getSortedPositions()
        );


        Board boardA = new Board();
        boardA.initialize();

        assertEquals(blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("BBKNNPPP") +
                        StringUtil.appendNewLine("PPPPPQRR") +
                        StringUtil.appendNewLine("bbknnppp") +
                        StringUtil.appendNewLine("pppppqrr"),
                boardA.getSortedPositions()
        );
    }

    public String startingBoard() {
        String blankRank = StringUtil.appendNewLine("........");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtil.appendNewLine("RNBQKBNR"));
        stringBuilder.append(StringUtil.appendNewLine("PPPPPPPP"));
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(StringUtil.appendNewLine("pppppppp"));
        stringBuilder.append(StringUtil.appendNewLine("rnbqkbnr"));

        return stringBuilder.toString();
    }
}
