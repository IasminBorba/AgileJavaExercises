package pieces;

public class AllTestsPieces {

    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(BishopPieceTest.class);
        suite.addTestSuite(KingPieceTest.class);
        suite.addTestSuite(KnightPieceTest.class);
        suite.addTestSuite(PawnPieceTest.class);
        suite.addTestSuite(QueenPieceTest.class);
        suite.addTestSuite(RookPieceTest.class);

        return suite;
    }
}