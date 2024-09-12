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
        String blankRank = StringUtil.appendNewLine("........");
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
        String blankRank = StringUtil.appendNewLine("........");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtil.appendNewLine("RNBQKBNR"));
        stringBuilder.append(StringUtil.appendNewLine("PPPPPPPP"));
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(blankRank);
        stringBuilder.append(StringUtil.appendNewLine("pppppppp"));
        stringBuilder.append(StringUtil.appendNewLine("rnbqkbnr"));

        return stringBuilder.toString();
    }
}
