package chess;

import pieces.*;
import util.StringUtil;
import junit.framework.TestCase;

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
        board.addPiece("b6", blackKing);
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

    public void testRemovePieces() {
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
        board.addPiece("b6", blackKing);
        assertEquals('K', board.getPieceRepresentation('b', 6));
        assertEquals(".K......", board.getRank(6));
        assertEquals(1, board.pieceCount());
        assertEquals(0, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        board.removePieceFromTheBoard(blackKing);
        assertEquals('.', board.getPieceRepresentation('b', 6));
        assertEquals("........", board.getRank(6));
        assertEquals(0, board.pieceCount());
        assertEquals(0, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());
    }

    public String startingBoard() {
        board.initialize();
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
