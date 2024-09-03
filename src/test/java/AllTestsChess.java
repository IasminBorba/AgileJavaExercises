import chess.BoardTest;
import chess.GameTest;

public class AllTestsChess {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(GameTest.class);
        return suite;
    }
}
