import chess.*;
import ui.*;
import util.*;
import pieces.AllTestsPieces;

public class AllTests {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(MovesHelperTest.class);
        suite.addTestSuite(PieceStrengthManagerTest.class);
        suite.addTestSuite(PositionTest.class);
        suite.addTest(AllTestsPieces.suite());
        suite.addTestSuite(BoardPrinterTest.class);
        suite.addTestSuite(CoordinateTransformerTest.class);
        suite.addTestSuite(PiecesCounterTest.class);
        suite.addTestSuite(GameUITest.class);
        suite.addTestSuite(ChessGameUIControllerTest.class);
        return suite;
    }
}
