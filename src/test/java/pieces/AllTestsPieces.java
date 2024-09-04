package pieces;

public class AllTestsPieces {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BishopTest.class);
        suite.addTestSuite(KingTest.class);
        suite.addTestSuite(KnightTest.class);
        suite.addTestSuite(PawnTest.class);
        suite.addTestSuite(QueenTest.class);
        suite.addTestSuite(RookTest.class);

        return suite;
    }
}