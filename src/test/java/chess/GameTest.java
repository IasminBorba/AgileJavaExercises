package chess;

import pieces.*;
import junit.framework.TestCase;
import util.StringUtil;

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
        board.addPiece(kingWhite,'d', 4);
        assertFalse(kingWhite.getPossibleMoves('a', 2));
        assertFalse(kingWhite.getPossibleMoves('a', 2));
        assertTrue(kingWhite.getPossibleMoves('d', 3));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        King kingBlack = King.create(Piece.Color.BLACK, board);
        board.addPiece(kingBlack,'h', 8);
        assertFalse(kingBlack.getPossibleMoves('i', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(kingBlack.getPossibleMoves('h', 7));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(kingBlack.getPossibleMoves('g', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testQueenMove() {
        board.createBoard();

        Queen queenWhite = Queen.create(Piece.Color.WHITE, board);
        board.addPiece(queenWhite,'d', 5);
        assertFalse(queenWhite.getPossibleMoves('a', 9));
        assertFalse(queenWhite.getPossibleMoves('c', 7));
        assertFalse(queenWhite.getPossibleMoves( 'e', 7));
        assertFalse(queenWhite.getPossibleMoves( 'c', 2));
        assertFalse(queenWhite.getPossibleMoves( 'e', 2));
        assertFalse(queenWhite.getPossibleMoves( 'b', 6));
        assertFalse(queenWhite.getPossibleMoves( 'b', 2));
        assertFalse(queenWhite.getPossibleMoves( 'f', 6));
        assertFalse(queenWhite.getPossibleMoves( 'f', 2));
        assertTrue(queenWhite.getPossibleMoves( 'g', 8));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Queen queenBlack = Queen.create(Piece.Color.BLACK, board);
        board.addPiece(queenBlack,'g', 5);
        assertFalse(queenBlack.getPossibleMoves('f', 7));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(queenBlack.getPossibleMoves('d', 2));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(queenBlack.getPossibleMoves('b', 1));
        assertTrue(queenBlack.getPossibleMoves('a', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertTrue(queenBlack.getPossibleMoves('c', 3));
    }

    public void testPawnMove() {
        board.createBoard();

        Pawn pawnWhite = Pawn.create(Piece.Color.WHITE, board);
        board.addPiece(pawnWhite,'d', 5);
        assertFalse(pawnWhite.getPossibleMoves('d', 7));
        assertFalse(pawnWhite.getPossibleMoves('c', 5));
        assertFalse(pawnWhite.getPossibleMoves( 'e', 5));
        assertFalse(pawnWhite.getPossibleMoves( 'c', 4));
        assertFalse(pawnWhite.getPossibleMoves( 'e', 4));
        assertFalse(pawnWhite.getPossibleMoves( 'c', 6));
        assertFalse(pawnWhite.getPossibleMoves( 'e', 6));
        assertTrue(pawnWhite.getPossibleMoves( 'd', 6));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Pawn pawnBlack = Pawn.create(Piece.Color.BLACK, board);
        board.addPiece(pawnBlack,'c', 7);
        assertFalse(pawnBlack.getPossibleMoves('c', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(pawnBlack.getPossibleMoves('c', 6));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(pawnBlack.getPossibleMoves('d', 6));
        assertTrue(pawnBlack.getPossibleMoves('c', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testBishopMove() {
        board.createBoard();

        Bishop bishopWhite = Bishop.create(Piece.Color.WHITE, board);
        board.addPiece(bishopWhite,'g', 2);
        assertFalse(bishopWhite.getPossibleMoves('g', 3));
        assertFalse(bishopWhite.getPossibleMoves('g', 1));
        assertFalse(bishopWhite.getPossibleMoves( 'f', 2));
        assertFalse(bishopWhite.getPossibleMoves( 'h', 2));
        assertTrue(bishopWhite.getPossibleMoves( 'c', 6));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Bishop bishopBlack = Bishop.create(Piece.Color.BLACK, board);
        board.addPiece(bishopBlack,'d', 5);
        assertFalse(bishopBlack.getPossibleMoves('c', 7));
        assertFalse(bishopBlack.getPossibleMoves('b', 6));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(bishopBlack.getPossibleMoves('f', 7));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(bishopBlack.getPossibleMoves('d', 2));
        assertTrue(bishopBlack.getPossibleMoves('c', 4));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());

        assertTrue(bishopBlack.getPossibleMoves('f', 1));
    }

    public void testRookMove() {
        board.createBoard();

        Rook rookWhite = Rook.create(Piece.Color.WHITE, board);
        board.addPiece(rookWhite,'a', 8);
        assertFalse(rookWhite.getPossibleMoves('a', 9));
        assertFalse(rookWhite.getPossibleMoves('i', 8));
        assertFalse(rookWhite.getPossibleMoves( 'b', 7));
        assertTrue(rookWhite.getPossibleMoves( 'a', 7));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Rook rookBlack = Rook.create(Piece.Color.BLACK, board);
        board.addPiece(rookBlack,'f', 5);
        assertFalse(rookBlack.getPossibleMoves('f', 9));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(rookBlack.getPossibleMoves('c', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(rookBlack.getPossibleMoves('f', 2));
        assertTrue(rookBlack.getPossibleMoves('c', 2));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testKnightMove() {
        board.createBoard();

        Knight knightWhite = Knight.create(Piece.Color.WHITE, board);
        board.addPiece(knightWhite,'d', 5);
        assertFalse(knightWhite.getPossibleMoves('d', 9));
        assertFalse(knightWhite.getPossibleMoves('d', 4));
        assertFalse(knightWhite.getPossibleMoves( 'd', 3));
        assertFalse(knightWhite.getPossibleMoves( 'd', 6));
        assertFalse(knightWhite.getPossibleMoves( 'd', 7));
        assertFalse(knightWhite.getPossibleMoves( 'e', 5));
        assertFalse(knightWhite.getPossibleMoves( 'f', 5));
        assertFalse(knightWhite.getPossibleMoves( 'c', 5));
        assertFalse(knightWhite.getPossibleMoves( 'b', 5));
        assertFalse(knightWhite.getPossibleMoves( 'e', 6));
        assertFalse(knightWhite.getPossibleMoves( 'e', 4));
        assertFalse(knightWhite.getPossibleMoves( 'c', 6));
        assertFalse(knightWhite.getPossibleMoves( 'c', 4));
        assertFalse(knightWhite.getPossibleMoves( 'f', 7));
        assertFalse(knightWhite.getPossibleMoves( 'f', 3));
        assertFalse(knightWhite.getPossibleMoves( 'b', 7));
        assertFalse(knightWhite.getPossibleMoves( 'b', 3));
        assertTrue(knightWhite.getPossibleMoves( 'c', 7));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Knight knightBlack = Knight.create(Piece.Color.BLACK, board);
        board.addPiece(knightBlack,'e', 3);
        assertFalse(knightBlack.getPossibleMoves('g', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(knightBlack.getPossibleMoves('c', 4));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(knightBlack.getPossibleMoves('a', 6));
        assertTrue(knightBlack.getPossibleMoves('d', 6));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}