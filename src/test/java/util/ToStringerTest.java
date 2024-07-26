package util;

import junit.framework.TestCase;

import chess.*;

public class ToStringerTest extends TestCase {
    public void testCreate() {
        Board board = new Board();
        board.initialize();

        System.out.println(ToStringer.toString(board));
    }
}
