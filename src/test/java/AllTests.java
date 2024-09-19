import chess.*;
import util.*;
import pieces.AllTestsPieces;

public class AllTests {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(GameTest.class);
        suite.addTestSuite(PositionTest.class);
        suite.addTest(AllTestsPieces.suite());
        suite.addTestSuite(PiecesCounterTest.class);
        suite.addTestSuite(PrintBoardTest.class);
        return suite;
    }
}
