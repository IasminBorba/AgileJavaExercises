package chess;
import pieces.Piece;
import util.StringUtil;
import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    public Board board;


    public void setUp() {
        board = new Board();
    }
    public void testCreate(){
        board.initialize();
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
        board.createBoard();

        assertEquals(0, board.pieceCount());
        assertEquals(0, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank +
                        blankRank + blankRank,
                board.print()
        );

        Piece blackKing = Piece.createBlackPiece(Piece.Type.KING);
        board.addPiece(blackKing, 'b', 6);
        assertEquals('K', board.getPiece('b', 6));

        Piece blackRook = Piece.createBlackPiece(Piece.Type.ROOK);
        board.addPiece(blackRook, 'b', 5);
        assertEquals('R', board.getPiece('b', 5));

        Piece whiteKing = Piece.createWhitePiece(Piece.Type.KING);
        board.addPiece(whiteKing, 'c', 4);
        assertEquals('k', board.getPiece('c', 4));

        System.out.println(board.print());
        assertEquals(
                blankRank + blankRank +
                        StringUtil.appendNewLine(".K......" )+
                        StringUtil.appendNewLine(".R......" ) +
                        StringUtil.appendNewLine("..k....." ) +
                        blankRank + blankRank + blankRank,
                board.print()
        );
    }
}
