package util;

import chess.Board;
import junit.framework.TestCase;

public class PrintBoardTest extends TestCase {
    public PrintBoard printBoard;
    public Board board;

    public void setUp() {
        board = new Board();
        printBoard = new PrintBoard(board);
    }

    public void testCreate() {
        String blankRank = ("........" + System.lineSeparator());
        assertEquals(blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                printBoard.print()
        );
    }

    public void testBoardPrintAfterInitialization() {
        board.initialize();
        assertEquals(startingBoard(), printBoard.print());
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
