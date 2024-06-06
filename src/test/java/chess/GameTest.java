package chess;

import pieces.Piece;
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
        assertFalse(board.addPiece(Piece.createBlackPiece(Piece.Type.KING), 'i', 8));


        board.addPiece(Piece.createBlackPiece(Piece.Type.KING), 'b', 8);
        board.addPiece(Piece.createBlackPiece(Piece.Type.ROOK), 'c', 8);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'a', 7);
        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'c', 7);
        board.addPiece(Piece.createBlackPiece(Piece.Type.BISHOP), 'd', 7);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'b', 6);
        board.addPiece(Piece.createBlackPiece(Piece.Type.QUEEN), 'e', 6);

        board.addPiece(Piece.createWhitePiece(Piece.Type.KNIGHT), 'f', 4);
        board.addPiece(Piece.createWhitePiece(Piece.Type.QUEEN), 'g', 4);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 3);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'h', 3);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 2);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'g', 2);

        board.addPiece(Piece.createWhitePiece(Piece.Type.ROOK), 'e', 1);
        board.addPiece(Piece.createWhitePiece(Piece.Type.KING), 'f', 1);


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

        Piece kingWhite = Piece.createWhitePiece(Piece.Type.KING);
        board.addPiece(kingWhite,'d', 4);
        assertFalse(game.movePiece(kingWhite, 'a', 2));
        assertTrue(game.movePiece(kingWhite, 'd', 3));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Piece kingBlack = Piece.createBlackPiece(Piece.Type.KING);
        board.addPiece(kingBlack,'h', 8);
        assertFalse(game.movePiece(kingBlack, 'i', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(game.movePiece(kingBlack, 'h', 7));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(game.movePiece(kingBlack, 'g', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }

    public void testQueenMove() {
        board.createBoard();

        Piece queenWhite = Piece.createWhitePiece(Piece.Type.QUEEN);
        board.addPiece(queenWhite,'a', 8);
        assertFalse(game.movePiece(queenWhite, 'a', 9));
        assertFalse(game.movePiece(queenWhite, 'i', 8));
        assertFalse(game.movePiece(queenWhite, 'b', 7));
        assertTrue(game.movePiece(queenWhite, 'a', 7));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Piece queenBlack = Piece.createBlackPiece(Piece.Type.QUEEN);
        board.addPiece(queenBlack,'f', 5);
        assertFalse(game.movePiece(queenBlack, 'f', 9));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(game.movePiece(queenBlack, 'c', 5));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
        

        assertFalse(game.movePiece(queenBlack, 'f', 2));
        assertTrue(game.movePiece(queenBlack, 'c', 2));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}