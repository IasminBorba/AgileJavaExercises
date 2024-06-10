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
        assertFalse(board.addPiece(KingPiece.create(Piece.Color.BLACK), 'i', 8));


        board.addPiece(KingPiece.create(Piece.Color.BLACK), 'b', 8);
        board.addPiece(RookPiece.create(Piece.Color.BLACK), 'c', 8);

        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'a', 7);
        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'c', 7);
        board.addPiece(BishopPiece.create(Piece.Color.BLACK), 'd', 7);

        board.addPiece(PawnPiece.create(Piece.Color.BLACK), 'b', 6);
        board.addPiece(QueenPiece.create(Piece.Color.BLACK), 'e', 6);

        board.addPiece(KnightPiece.create(Piece.Color.WHITE), 'f', 4);
        board.addPiece(QueenPiece.create(Piece.Color.WHITE), 'g', 4);

        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'f', 3);
        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'h', 3);

        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'f', 2);
        board.addPiece(PawnPiece.create(Piece.Color.WHITE), 'g', 2);

        board.addPiece(RookPiece.create(Piece.Color.WHITE), 'e', 1);
        board.addPiece(KingPiece.create(Piece.Color.WHITE), 'f', 1);


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
}