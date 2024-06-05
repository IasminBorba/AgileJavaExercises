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
        assertEquals("........", emptyRank);

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
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
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

        assertEquals(
                blankRank + blankRank +
                        StringUtil.appendNewLine(".K......" )+
                        StringUtil.appendNewLine(".R......" ) +
                        StringUtil.appendNewLine("..k....." ) +
                        blankRank + blankRank + blankRank,
                board.print()
        );
    }

    public void testCalculateStrength() {
        board.createBoard();
        assertFalse(board.addPiece(Piece.createBlackPiece(Piece.Type.KING), 'i', 8));


        board.addPiece(Piece.createBlackPiece(Piece.Type.KING), 'b', 8);
        board.addPiece(Piece.createBlackPiece(Piece.Type.ROOK), 'c', 8);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'a', 7);
        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'c', 7);
        board.addPiece(Piece.createBlackPiece(Piece.Type.BISHOP), 'd', 7);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'b', 6);
        board.addPiece(Piece.createBlackPiece(Piece.Type.QUEEN), 'e', 6);

        board.addPiece(Piece.createWhitePiece(Piece.Type.KNIGHT), 'f', 4);
        board.addPiece(Piece.createWhitePiece(Piece.Type.QUEEN), 'g', 4);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 3);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'h', 3);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 2);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'g', 2);

        board.addPiece(Piece.createWhitePiece(Piece.Type.ROOK), 'e', 1);
        board.addPiece(Piece.createWhitePiece(Piece.Type.KING), 'f', 1);


        assertEquals(19.5, board.getStrengthWhitePiece(), 0.001);
        assertEquals(20, board.getStrengthBlackPiece(), 0.001);

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine(".KR.....") +
                        StringUtil.appendNewLine("P.PB....") +
                        StringUtil.appendNewLine(".P..Q...") +
                        blankRank +
                        StringUtil.appendNewLine(".....nq.") +
                        StringUtil.appendNewLine(".....p.p") +
                        StringUtil.appendNewLine(".....pp.") +
                        StringUtil.appendNewLine("....rk.."),
                board.print()
        );
    }

    public void testkingMove() {
        board.createBoard();

        Piece kingWhite = Piece.createWhitePiece(Piece.Type.KING);
        board.addPiece(kingWhite,'d', 4);
        assertFalse(board.newKingPosition(kingWhite, 'a', 2));
        assertTrue(board.newKingPosition(kingWhite, 'd', 3));

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(0, board.getPiecesBlack());


        Piece kingBlack = Piece.createBlackPiece(Piece.Type.KING);
        board.addPiece(kingBlack,'h', 8);
        assertFalse(board.newKingPosition(kingBlack, 'i', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(board.newKingPosition(kingBlack, 'h', 7));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());


        assertTrue(board.newKingPosition(kingBlack, 'g', 8));
        assertEquals(2, board.pieceCount());
        assertEquals(1, board.getPiecesWhite());
        assertEquals(1, board.getPiecesBlack());
    }
}
