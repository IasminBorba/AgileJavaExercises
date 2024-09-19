package util;

import chess.Board;
import junit.framework.TestCase;

public class BoardPrinterTest extends TestCase {
    public BoardPrinter boardPrinter;
    public Board board;

    public void setUp() {
        board = new Board();
        boardPrinter = new BoardPrinter(board);
    }

    public void testCreate() {
        String blankRank = ("........" + System.lineSeparator());
        assertEquals(blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                boardPrinter.visualize()
        );
    }

    public void testBoardVisualizeAfterInitialization() {
        board.initialize();
        assertEquals(startingBoard(), boardPrinter.visualize());
    }

    public String startingBoard() {
        String blankRank = ("........" + System.lineSeparator());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RNBQKBNR" + System.lineSeparator());
        stringBuilder.append("PPPPPPPP" + System.lineSeparator());
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append("pppppppp" + System.lineSeparator());
        stringBuilder.append("rnbqkbnr" + System.lineSeparator());

        return stringBuilder.toString();
    }
}
