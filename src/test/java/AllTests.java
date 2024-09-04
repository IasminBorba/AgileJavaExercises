import chess.*;
import pieces.AllTestsPieces;

public class AllTests {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(GameTest.class);
        suite.addTest(AllTestsPieces.suite());
        return suite;
    }
}
