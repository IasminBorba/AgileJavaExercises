package chess;

import pieces.*;
import junit.framework.TestCase;
import util.StringUtil;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

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
        board.createBoard();
        assertFalse(board.addPiece(King.create(Piece.Color.BLACK, board), 'i', 8));


        board.addPiece(King.create(Piece.Color.BLACK, board), 'b', 8);
        board.addPiece(Rook.create(Piece.Color.BLACK,board), 'c', 8);

        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'a', 7);
        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'c', 7);
        board.addPiece(Bishop.create(Piece.Color.BLACK, board), 'd', 7);

        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'b', 6);
        board.addPiece(Queen.create(Piece.Color.BLACK, board), 'e', 6);

        board.addPiece(Knight.create(Piece.Color.WHITE, board), 'f', 4);
        board.addPiece(Queen.create(Piece.Color.WHITE, board), 'g', 4);

        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'f', 3);
        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'h', 3);

        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'f', 2);
        board.addPiece(Pawn.create(Piece.Color.BLACK, board), 'g', 2);

        board.addPiece(Rook.create(Piece.Color.WHITE, board), 'e', 1);
        board.addPiece(King.create(Piece.Color.WHITE, board), 'f', 1);


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

        King kingWhite = King.create(Piece.Color.WHITE, board);
        board.put("d4", kingWhite);
        ArrayList<String> possibleMoves = kingWhite.getPossibleMoves("d4");
        assertFalse(possibleMoves.contains("b2"));
        assertFalse(possibleMoves.contains("b4"));
        assertFalse(possibleMoves.contains("f4"));
        assertTrue(possibleMoves.contains("d5"));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        King kingBlack = King.create(Piece.Color.BLACK, board);
        board.put("h8", kingBlack);
        possibleMoves = kingBlack.getPossibleMoves("h8");
        assertFalse(possibleMoves.contains("i8"));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("h7"));
        board.movePiece("h7", kingBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(possibleMoves.contains("g8"));
        board.movePiece("g8", kingBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
//
//    public void testQueenMove() {
//        board.createBoard();
//
//        Queen queenWhite = Queen.create(Piece.Color.WHITE, board);
//        board.addPiece(queenWhite,'d', 5);
//        assertFalse(queenWhite.getPossibleMoves('a', 9));
//        assertFalse(queenWhite.getPossibleMoves('c', 7));
//        assertFalse(queenWhite.getPossibleMoves( 'e', 7));
//        assertFalse(queenWhite.getPossibleMoves( 'c', 2));
//        assertFalse(queenWhite.getPossibleMoves( 'e', 2));
//        assertFalse(queenWhite.getPossibleMoves( 'b', 6));
//        assertFalse(queenWhite.getPossibleMoves( 'b', 2));
//        assertFalse(queenWhite.getPossibleMoves( 'f', 6));
//        assertFalse(queenWhite.getPossibleMoves( 'f', 2));
//        assertTrue(queenWhite.getPossibleMoves( 'g', 8));
//
//        assertEquals(1, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(0, board.getPiecesBlack());
//
//
//        Queen queenBlack = Queen.create(Piece.Color.BLACK, board);
//        board.addPiece(queenBlack,'g', 5);
//        assertFalse(queenBlack.getPossibleMoves('f', 7));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(queenBlack.getPossibleMoves('d', 2));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertFalse(queenBlack.getPossibleMoves('b', 1));
//        assertTrue(queenBlack.getPossibleMoves('a', 5));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//        assertTrue(queenBlack.getPossibleMoves('c', 3));
//    }

    public void testPawnMove() {
        board.createBoard();

        Pawn pawnWhite = Pawn.create(Piece.Color.WHITE, board);
        board.put("d5", pawnWhite);
        assertNotEquals("d7", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("c5", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("e5", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("c4", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("e4", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("c6", pawnWhite.getPossibleMoves("d5").getFirst());
        assertNotEquals("e6", pawnWhite.getPossibleMoves("d5").getFirst());
        assertEquals("d6", pawnWhite.getPossibleMoves( "d5").getFirst());

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Pawn pawnBlack = Pawn.create(Piece.Color.BLACK, board);
        board.put("c7", pawnBlack);
        assertNotEquals("c5", pawnBlack.getPossibleMoves("c7").getFirst());
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertEquals("c6", pawnBlack.getPossibleMoves( "c7").getFirst());
        board.movePiece("c6", pawnBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertNotEquals("d6", pawnBlack.getPossibleMoves("c6").getFirst());
        assertEquals("c5", pawnBlack.getPossibleMoves( "c6").getFirst());
        board.movePiece("c6", pawnBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

//    public void testBishopMove() {
//        board.createBoard();
//
//        Bishop bishopWhite = Bishop.create(Piece.Color.WHITE, board);
//        board.addPiece(bishopWhite,'g', 2);
//        assertFalse(bishopWhite.getPossibleMoves('g', 3));
//        assertFalse(bishopWhite.getPossibleMoves('g', 1));
//        assertFalse(bishopWhite.getPossibleMoves( 'f', 2));
//        assertFalse(bishopWhite.getPossibleMoves( 'h', 2));
//        assertTrue(bishopWhite.getPossibleMoves( 'c', 6));
//
//        assertEquals(1, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(0, board.getPiecesBlack());
//
//
//        Bishop bishopBlack = Bishop.create(Piece.Color.BLACK, board);
//        board.addPiece(bishopBlack,'d', 5);
//        assertFalse(bishopBlack.getPossibleMoves('c', 7));
//        assertFalse(bishopBlack.getPossibleMoves('b', 6));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(bishopBlack.getPossibleMoves('f', 7));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertFalse(bishopBlack.getPossibleMoves('d', 2));
//        assertTrue(bishopBlack.getPossibleMoves('c', 4));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//        assertTrue(bishopBlack.getPossibleMoves('f', 1));
//    }
//
//    public void testRookMove() {
//        board.createBoard();
//
//        Rook rookWhite = Rook.create(Piece.Color.WHITE, board);
//        board.addPiece(rookWhite,'a', 8);
//        assertFalse(rookWhite.getPossibleMoves('a', 9));
//        assertFalse(rookWhite.getPossibleMoves('i', 8));
//        assertFalse(rookWhite.getPossibleMoves( 'b', 7));
//        assertTrue(rookWhite.getPossibleMoves( 'a', 7));
//
//        assertEquals(1, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(0, board.getPiecesBlack());
//
//
//        Rook rookBlack = Rook.create(Piece.Color.BLACK, board);
//        board.addPiece(rookBlack,'f', 5);
//        assertFalse(rookBlack.getPossibleMoves('f', 9));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(rookBlack.getPossibleMoves('c', 5));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertFalse(rookBlack.getPossibleMoves('f', 2));
//        assertTrue(rookBlack.getPossibleMoves('c', 2));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//    }
//
    public void testKnightMove() {
        board.createBoard();

        Knight knightWhite = Knight.create(Piece.Color.WHITE, board);
        board.put("d5", knightWhite);
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


        Knight knightBlack = Knight.create(Piece.Color.BLACK, board);
        board.put("e3", knightBlack);
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

    System.out.println(possibleMoves);

        assertFalse(possibleMoves.contains("a6"));
        assertTrue(possibleMoves.contains("d6"));
        board.movePiece("d6", knightBlack);
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}