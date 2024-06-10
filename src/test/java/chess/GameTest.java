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
        assertFalse(board.addPiece(KingPiece.create(Piece.Color.BLACK, board), 'i', 8));


        board.addPiece(KingPiece.create(Piece.Color.BLACK, board), 'b', 8);
        board.addPiece(RookPiece.create(Piece.Color.BLACK), 'c', 8);

        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'a', 7);
        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'c', 7);
        board.addPiece(BishopPiece.create(Piece.Color.BLACK), 'd', 7);

        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'b', 6);
        board.addPiece(QueenPiece.create(Piece.Color.BLACK, board), 'e', 6);

        board.addPiece(KnightPiece.create(Piece.Color.WHITE), 'f', 4);
        board.addPiece(QueenPiece.create(Piece.Color.WHITE, board), 'g', 4);

        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'f', 3);
        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'h', 3);

        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'f', 2);
        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'g', 2);

        board.addPiece(RookPiece.create(Piece.Color.WHITE), 'e', 1);
        board.addPiece(KingPiece.create(Piece.Color.WHITE, board), 'f', 1);


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

        Piece kingWhite = KingPiece.create(Piece.Color.WHITE, board);
        board.addPiece(kingWhite,'d', 4);
        assertFalse(kingWhite.getPossibleMoves('a', 2));
        assertFalse(kingWhite.getPossibleMoves('a', 2));
        assertTrue(kingWhite.getPossibleMoves('d', 3));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Piece kingBlack = KingPiece.create(Piece.Color.BLACK, board);
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

        QueenPiece queenWhite = QueenPiece.create(Piece.Color.WHITE, board);
        board.addPiece(queenWhite,'a', 8);
        assertFalse(queenWhite.getPossibleMoves('a', 9));
        assertFalse(queenWhite.getPossibleMoves('i', 8));
        assertFalse(queenWhite.getPossibleMoves( 'b', 7));
        assertTrue(queenWhite.getPossibleMoves( 'a', 7));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Piece queenBlack = QueenPiece.create(Piece.Color.BLACK, board);
        board.addPiece(queenBlack,'f', 5);
        assertFalse(queenBlack.getPossibleMoves('f', 9));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(queenBlack.getPossibleMoves('c', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertFalse(queenBlack.getPossibleMoves('f', 2));
        assertTrue(queenBlack.getPossibleMoves('c', 2));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}