package chess;
import util.StringUtil;
import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    public Board board;


    public void setUp() {
        board = new Board();
    }
    public void testCreate(){
        assertEquals(32, board.pieceCount());
        assertEquals(16, board.getPiecesWhite());
        assertEquals(16, board.getPiecesBlack());

        String firstRank = board.getRank(8);
        assertEquals("RNBQKBNR", firstRank);

        String secondRank = board.getRank(7);
        assertEquals("PPPPPPPP", secondRank);

        String emptyRank = board.getRank(4);
        assertEquals("", emptyRank);

        String seventhRank = board.getRank(2);
        assertEquals("pppppppp", seventhRank);


        String eighthRank = board.getRank(1);
        assertEquals("rnbqkbnr", eighthRank);

        char pieceA8 = board.getPiece('a',8);
        assertEquals('R', pieceA8);

        char pieceE1 = board.getPiece('e',1);
        assertEquals('k', pieceE1);

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
        StringUtil.appendNewLine("rnbqkbnr") +
                StringUtil.appendNewLine("pppppppp") +
                blankRank + blankRank + blankRank + blankRank +
                StringUtil.appendNewLine("PPPPPPPP") +
                StringUtil.appendNewLine("RNBQKBNR"),
                board.print()
        );
    }

    public void testCreateBoard(){

    }
}
