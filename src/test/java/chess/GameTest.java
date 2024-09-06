package chess;

import pieces.*;
import junit.framework.TestCase;
import util.StringUtil;

import java.util.ArrayList;

public class GameTest extends TestCase {
    private Board board;
    private Game game;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        board = new Board();
        game = new Game(board);
    }

    public void testCalculateStrength() {
        board.addPiece(King.create(Piece.Color.BLACK), "b8");
        board.addPiece(Rook.create(Piece.Color.BLACK), "c8");

        board.addPiece(Pawn.create(Piece.Color.BLACK), "a7");
        board.addPiece(Pawn.create(Piece.Color.BLACK), "c7");
        board.addPiece(Bishop.create(Piece.Color.BLACK), "d7");

        board.addPiece(Pawn.create(Piece.Color.BLACK), "b6");
        board.addPiece(Queen.create(Piece.Color.BLACK), "e6");

        board.addPiece(Knight.create(Piece.Color.WHITE), "f4");
        board.addPiece(Queen.create(Piece.Color.WHITE), "g4");

        board.addPiece(Pawn.create(Piece.Color.WHITE), "f3");
        board.addPiece(Pawn.create(Piece.Color.WHITE), "h3");

        board.addPiece(Pawn.create(Piece.Color.WHITE), "f2");
        board.addPiece(Pawn.create(Piece.Color.WHITE), "g2");

        board.addPiece(Rook.create(Piece.Color.WHITE), "e1");
        board.addPiece(King.create(Piece.Color.WHITE), "f1");


        assertEquals(19.5, game.getStrengthWhitePiece(), 0.001);
        assertEquals(20, game.getStrengthBlackPiece(), 0.001);


        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine(".KR.....") +
                        StringUtil.appendNewLine("P.PB....") +
                        StringUtil.appendNewLine(".P..Q...") +
                        blankRank +
                        StringUtil.appendNewLine(".....nq.") +
                        StringUtil.appendNewLine(".....p.p") +
                        StringUtil.appendNewLine(".....pp.") +
                        StringUtil.appendNewLine("....rk.."),
                board.print()
        );
    }

    public void testKingMove() {
        board.createBoard();

        King kingWhite = King.create(Piece.Color.WHITE);
        board.addPiece(kingWhite, "d4");
        ArrayList<String> possibleMoves = kingWhite.getPossibleMoves("d4");
        assertFalse(possibleMoves.contains("b2"));
        assertFalse(possibleMoves.contains("b4"));
        assertFalse(possibleMoves.contains("f4"));
        assertTrue(possibleMoves.contains("d5"));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        King kingBlack = King.create(Piece.Color.BLACK);
        board.addPiece(kingBlack, "h8");
        possibleMoves = kingBlack.getPossibleMoves("h8");
        assertFalse(possibleMoves.contains("i8"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("h7"));
        board.movePiece("h7", kingBlack);
        possibleMoves = kingBlack.getPossibleMoves("h7");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("g8"));
        board.movePiece("g8", kingBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testQueenMove() {
        board.createBoard();

        Queen queenWhite = Queen.create(Piece.Color.WHITE);
        board.addPiece(queenWhite, "d5");
        ArrayList<String> possibleMoves = queenWhite.getPossibleMoves("d5");
        assertFalse(possibleMoves.contains("a9"));
        assertFalse(possibleMoves.contains("b2"));
        assertFalse(possibleMoves.contains("b6"));
        assertFalse(possibleMoves.contains("c2"));
        assertFalse(possibleMoves.contains("c7"));
        assertFalse(possibleMoves.contains("e7"));
        assertFalse(possibleMoves.contains("e2"));
        assertFalse(possibleMoves.contains("f6"));
        assertFalse(possibleMoves.contains("f2"));
        assertTrue(possibleMoves.contains("g8"));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        Queen queenBlack = Queen.create(Piece.Color.BLACK);
        board.addPiece(queenBlack, "g5");
        possibleMoves = queenBlack.getPossibleMoves("g5");
        assertFalse(possibleMoves.contains("f7"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("d2"));
        board.movePiece("d2", queenBlack);
        possibleMoves = queenBlack.getPossibleMoves("d2");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("a5"));
        board.movePiece("a5", queenBlack);
        possibleMoves = queenBlack.getPossibleMoves("a5");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertTrue(possibleMoves.contains("c3"));
        board.movePiece("c3", queenBlack);
    }

    public void testPawnMove() {
        board.createBoard();

        Pawn pawnWhite = Pawn.create(Piece.Color.WHITE);
        board.addPiece(pawnWhite, "d5");
        ArrayList<String> possibleMoves = pawnWhite.getPossibleMoves("d5");
        assertFalse(possibleMoves.contains("d7"));
        assertFalse(possibleMoves.contains("c5"));
        assertFalse(possibleMoves.contains("e5"));
        assertFalse(possibleMoves.contains("c4"));
        assertFalse(possibleMoves.contains("e4"));
        assertFalse(possibleMoves.contains("c6"));
        assertFalse(possibleMoves.contains("e6"));
        assertTrue(possibleMoves.contains("d6"));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        Pawn pawnBlack = Pawn.create(Piece.Color.BLACK);
        board.addPiece(pawnBlack, "c7");
        possibleMoves = pawnBlack.getPossibleMoves("c7");
        assertFalse(possibleMoves.contains("c5"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("c6"));
        board.movePiece("c6", pawnBlack);
        possibleMoves = pawnBlack.getPossibleMoves("c6");

        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(possibleMoves.contains("d6"));
        assertTrue(possibleMoves.contains("c5"));
        board.movePiece("c5", pawnBlack);

        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testBishopMove() {
        board.createBoard();

        Bishop bishopWhite = Bishop.create(Piece.Color.WHITE);
        board.addPiece(bishopWhite, "g2");
        ArrayList<String> possibleMoves = bishopWhite.getPossibleMoves("d5");
        assertFalse(possibleMoves.contains("g3"));
        assertFalse(possibleMoves.contains("g1"));
        assertFalse(possibleMoves.contains("f2"));
        assertFalse(possibleMoves.contains("h2"));
        assertTrue(possibleMoves.contains("c6"));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        Bishop bishopBlack = Bishop.create(Piece.Color.BLACK);
        board.addPiece(bishopBlack, "d5");
        possibleMoves = bishopBlack.getPossibleMoves("d5");

        assertFalse(possibleMoves.contains("c7"));
        assertFalse(possibleMoves.contains("b6"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertTrue(possibleMoves.contains("f7"));
        board.movePiece("f7", bishopBlack);
        possibleMoves = bishopBlack.getPossibleMoves("f7");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(possibleMoves.contains("d2"));
        assertTrue(possibleMoves.contains("c4"));
        board.movePiece("c4", bishopBlack);
        possibleMoves = bishopBlack.getPossibleMoves("c4");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("f1"));
        board.movePiece("c4", bishopBlack);
    }

    public void testRookMove() {
        board.createBoard();

        Rook rookWhite = Rook.create(Piece.Color.WHITE);
        board.addPiece(rookWhite, "a8");
        ArrayList<String> possibleMoves = rookWhite.getPossibleMoves("a8");
        assertFalse(possibleMoves.contains("a9"));
        assertFalse(possibleMoves.contains("b7"));
        assertTrue(possibleMoves.contains("a7"));
        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        Rook rookBlack = Rook.create(Piece.Color.BLACK);
        board.addPiece(rookBlack, "f5");
        possibleMoves = rookBlack.getPossibleMoves("f5");
        assertFalse(possibleMoves.contains("f9"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("c5"));
        board.movePiece("c5", rookBlack);
        possibleMoves = rookBlack.getPossibleMoves("c5");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(possibleMoves.contains("f2"));
        assertTrue(possibleMoves.contains("c2"));
        board.movePiece("c2", rookBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testKnightMove() {
        board.createBoard();

        Knight knightWhite = Knight.create(Piece.Color.WHITE);
        board.addPiece(knightWhite, "d5");
        ArrayList<String> possibleMoves = knightWhite.getPossibleMoves("d5");
        assertFalse(possibleMoves.contains("d9"));
        assertFalse(possibleMoves.contains("d4"));
        assertFalse(possibleMoves.contains("d3"));
        assertFalse(possibleMoves.contains("d6"));
        assertFalse(possibleMoves.contains("d7"));
        assertFalse(possibleMoves.contains("b3"));
        assertFalse(possibleMoves.contains("b5"));
        assertFalse(possibleMoves.contains("b7"));
        assertFalse(possibleMoves.contains("c2"));
        assertFalse(possibleMoves.contains("c4"));
        assertFalse(possibleMoves.contains("c5"));
        assertFalse(possibleMoves.contains("c6"));
        assertFalse(possibleMoves.contains("c8"));
        assertFalse(possibleMoves.contains("e2"));
        assertFalse(possibleMoves.contains("e4"));
        assertFalse(possibleMoves.contains("e5"));
        assertFalse(possibleMoves.contains("e6"));
        assertFalse(possibleMoves.contains("e8"));
        assertFalse(possibleMoves.contains("f3"));
        assertFalse(possibleMoves.contains("f5"));
        assertFalse(possibleMoves.contains("f7"));
        assertTrue(possibleMoves.contains("e7"));


        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        possibleMoves.clear();

        Knight knightBlack = Knight.create(Piece.Color.BLACK);
        board.addPiece(knightBlack, "e3");
        possibleMoves = knightBlack.getPossibleMoves("e3");

        assertFalse(possibleMoves.contains("g5"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("c4"));
        board.movePiece("c4", knightBlack);
        possibleMoves = knightBlack.getPossibleMoves("c4");
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(possibleMoves.contains("a6"));
        assertTrue(possibleMoves.contains("d6"));
        board.movePiece("d6", knightBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}