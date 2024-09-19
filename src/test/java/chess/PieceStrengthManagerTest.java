package chess;

import pieces.*;
import junit.framework.TestCase;
import util.TransformCoordenate;
import pieces.Piece.*;

public class PieceStrengthManagerTest extends TestCase {
    private Board board;
    private PieceStrengthManager pieceStrengthManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        board = new Board();
        pieceStrengthManager = new PieceStrengthManager(board);
    }

    public void testCalculateStrengthWhitePieces() {
        board.addPiece(Pawn.createPiece(Color.WHITE), "c5");
        board.addPiece(Queen.createPiece(Color.WHITE), "d2");

        assertEquals(10, pieceStrengthManager.calculateWhitePieceStrength(), 0.001);
    }

    public void testCalculateStrengthBlackPieces() {
        board.addPiece(Bishop.createPiece(Color.BLACK), "a1");
        board.addPiece(Rook.createPiece(Color.BLACK), "b1");
        board.addPiece(Knight.createPiece(Color.BLACK), "c1");

        assertEquals(10.5, pieceStrengthManager.calculateBlackPieceStrength(), 0.001);
    }

    public void testHasDuplicatePawnsInColumn() {
        board.addPiece(Pawn.createPiece(Color.WHITE), "b4");
        board.addPiece(Pawn.createPiece(Color.WHITE), "b5");

        assertTrue(pieceStrengthManager.hasDuplicatePawnsInFile(board.getBoardCells(), TransformCoordenate.convertColumnToIndex('b')));
    }

    public void testHasNotDuplicatePawnsInColumn() {
        board.addPiece(Pawn.createPiece(Color.WHITE), "a7");
        board.addPiece(Pawn.createPiece(Color.BLACK), "a1");

        assertFalse(pieceStrengthManager.hasDuplicatePawnsInFile(board.getBoardCells(), TransformCoordenate.convertColumnToIndex('a')));
    }

    public void testCalculateStrengthPatternPieces() {
        addPatternPieces();

        assertEquals(19.5, pieceStrengthManager.calculateWhitePieceStrength(), 0.001);
        assertEquals(20, pieceStrengthManager.calculateBlackPieceStrength(), 0.001);
    }

    public void addPatternPieces() {
        board.addPiece(King.createPiece(Color.BLACK), "b8");
        board.addPiece(Rook.createPiece(Color.BLACK), "c8");

        board.addPiece(Pawn.createPiece(Color.BLACK), "a7");
        board.addPiece(Pawn.createPiece(Color.BLACK), "c7");
        board.addPiece(Bishop.createPiece(Color.BLACK), "d7");

        board.addPiece(Pawn.createPiece(Color.BLACK), "b6");
        board.addPiece(Queen.createPiece(Color.BLACK), "e6");

        board.addPiece(Knight.createPiece(Color.WHITE), "f4");
        board.addPiece(Queen.createPiece(Color.WHITE), "g4");

        board.addPiece(Pawn.createPiece(Color.WHITE), "f3");
        board.addPiece(Pawn.createPiece(Color.WHITE), "h3");

        board.addPiece(Pawn.createPiece(Color.WHITE), "f2");
        board.addPiece(Pawn.createPiece(Color.WHITE), "g2");

        board.addPiece(Rook.createPiece(Color.WHITE), "e1");
        board.addPiece(King.createPiece(Color.WHITE), "f1");
    }
}