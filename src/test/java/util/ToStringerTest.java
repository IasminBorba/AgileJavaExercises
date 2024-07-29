package util;

import junit.framework.TestCase;

import chess.*;

public class ToStringerTest extends TestCase {
    public void testCreate() {
        Board board = new Board();
        board.initialize();

        String boardStr = "Board fields annotation @Dump:\n" +
                            "\tboard\n" +
                            "\tpiecesOnTheBoard\n" +
                            "\tfilename";

        assertEquals(boardStr, ToStringer.toString(board));
    }
}
