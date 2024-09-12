package util;

import chess.Board;
import junit.framework.TestCase;

public class PiecesCounterTest extends TestCase {
    public PiecesCounter piecesCounter;
    public Board board;

    public void setUp() {
        board = new Board();
        board.initialize();

        piecesCounter = new PiecesCounter(board);
    }

    public void testPieceCountAfterInitialization() {
        assertEquals(32, piecesCounter.counter());
    }

    public void testWhitePiecesCountAfterInitialization() {
        assertEquals(16, piecesCounter.whiteCount());
    }

    public void testBlackPiecesCountAfterInitialization() {
        assertEquals(16, piecesCounter.blackCount());
    }
}
