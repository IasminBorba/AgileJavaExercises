package chess;

import pieces.*;
import junit.framework.TestCase;
import util.TransformCoordenate;
import pieces.Piece.*;

public class GameTest extends TestCase {
    private Board board;
    private Game game;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        board = new Board();
        game = new Game(board);
    }

    public void testCalculateStrengthWhitePieces() {
        board.addPiece(Pawn.create(Color.WHITE), "c5");
        board.addPiece(Queen.create(Color.WHITE), "d2");

        assertEquals(10, game.getStrengthWhitePiece(), 0.001);
    }

    public void testCalculateStrengthBlackPieces() {
        board.addPiece(Bishop.create(Color.BLACK), "a1");
        board.addPiece(Rook.create(Color.BLACK), "b1");
        board.addPiece(Knight.create(Color.BLACK), "c1");

        assertEquals(10.5, game.getStrengthBlackPiece(), 0.001);
    }

    public void testHasDuplicatePawnsInColumn() {
        board.addPiece(Pawn.create(Color.WHITE), "b4");
        board.addPiece(Pawn.create(Color.WHITE), "b5");

        assertTrue(game.hasDuplicatePawnsInColumn(board.getBoard(), TransformCoordenate.convertColumnToIndex('b')));
    }

    public void testHasNotDuplicatePawnsInColumn() {
        board.addPiece(Pawn.create(Color.WHITE), "a7");
        board.addPiece(Pawn.create(Color.BLACK), "a1");

        assertFalse(game.hasDuplicatePawnsInColumn(board.getBoard(), TransformCoordenate.convertColumnToIndex('a')));
    }

    public void testCalculateStrengthPatternPieces() {
        addPatternPieces();

        assertEquals(19.5, game.getStrengthWhitePiece(), 0.001);
        assertEquals(20, game.getStrengthBlackPiece(), 0.001);
    }

    public void addPatternPieces() {
        board.addPiece(King.create(Color.BLACK), "b8");
        board.addPiece(Rook.create(Color.BLACK), "c8");

        board.addPiece(Pawn.create(Color.BLACK), "a7");
        board.addPiece(Pawn.create(Color.BLACK), "c7");
        board.addPiece(Bishop.create(Color.BLACK), "d7");

        board.addPiece(Pawn.create(Color.BLACK), "b6");
        board.addPiece(Queen.create(Color.BLACK), "e6");

        board.addPiece(Knight.create(Color.WHITE), "f4");
        board.addPiece(Queen.create(Color.WHITE), "g4");

        board.addPiece(Pawn.create(Color.WHITE), "f3");
        board.addPiece(Pawn.create(Color.WHITE), "h3");

        board.addPiece(Pawn.create(Color.WHITE), "f2");
        board.addPiece(Pawn.create(Color.WHITE), "g2");

        board.addPiece(Rook.create(Color.WHITE), "e1");
        board.addPiece(King.create(Color.WHITE), "f1");
    }
}