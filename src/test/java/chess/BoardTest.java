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
        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(blankRank + blankRank +
                            blankRank + blankRank +
                            blankRank + blankRank +
                            blankRank + blankRank,
                        board.print()
        );

        assertEquals(0, board.pieceCount());
    }

    public void testBoardPrintAfterInitialization() {
        board.initialize();
        assertEquals(startingBoard(), board.print());
    }

    public void testPieceCountAfterInitialization() {
        board.initialize();
        assertEquals(32, board.pieceCount());
    }

    public void testWhitePiecesCountAfterInitialization() {
        board.initialize();
        assertEquals(16, board.getPiecesWhite());
    }

    public void testBlackPiecesCountAfterInitialization() {
        board.initialize();
        assertEquals(16, board.getPiecesBlack());
    }

    public void testAddPieceToBoard() {
        Piece blackKing = King.create(Piece.Color.BLACK);
        board.addPiece(blackKing, "b6");

        assertTrue(board.pieceCount() == 1);
    }

    public void testNotAddPieceInTheBoard() {
        Piece whitePawn = Pawn.create(Piece.Color.WHITE);
        board.addPiece(whitePawn, "c9");

        assertTrue(board.pieceCount() == 0);
    }

    public void testRemovePieces() {
        Piece blackBishop = Bishop.create(Piece.Color.BLACK);
        board.addPiece(blackBishop, "b6");
        assertEquals(1, board.pieceCount());

        board.removePieceFromTheBoard(blackBishop);
        assertEquals(0, board.pieceCount());
    }

    public void testMovePiece() {
        Piece whiteQueen = Queen.create(Piece.Color.WHITE);
        board.addPiece(whiteQueen, "b6");

        board.movePiece("b7", whiteQueen);

        assertEquals('q', board.getPieceRepresentation('b', 7));
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
