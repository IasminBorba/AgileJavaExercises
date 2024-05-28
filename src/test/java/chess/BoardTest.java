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
        board = new Board();
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
    public void testEvaluationFunction(){
        board = new Board();
        board.createBoard();

        Piece blackQueen = Piece.createBlackPiece(Piece.Type.QUEEN);
        board.addPiece(blackQueen, 'e', 6);
        assertEquals(0.0, board.getEvoluationWhitePieces(), 0.001);
        assertEquals(9.0, board.getEvoluationBlackPieces(), 0.001);

        Piece whiteRook = Piece.createWhitePiece(Piece.Type.ROOK);
        board.addPiece(whiteRook, 'e', 1);
        assertEquals(5.0, board.getEvoluationWhitePieces(), 0.001);
        assertEquals(9.0, board.getEvoluationBlackPieces(), 0.001);

        Piece blackBishop = Piece.createBlackPiece(Piece.Type.BISHOP);
        board.addPiece(blackBishop, 'd', 7);
        assertEquals(5.0, board.getEvoluationWhitePieces(), 0.001);
        assertEquals(12.0, board.getEvoluationBlackPieces(), 0.001);

        board.addPiece(Piece.createBlackPiece(Piece.Type.ROOK), 'c', 8);
        board.addPiece(Piece.createBlackPiece(Piece.Type.KING), 'b', 8);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'c', 7);
        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'a', 7);

        board.addPiece(Piece.createBlackPiece(Piece.Type.PAWN), 'a', 6);

        board.addPiece(Piece.createWhitePiece(Piece.Type.QUEEN), 'g', 4);
        board.addPiece(Piece.createWhitePiece(Piece.Type.KNIGHT), 'f', 4);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'H', 3);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 3);

        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'g', 2);
        board.addPiece(Piece.createWhitePiece(Piece.Type.PAWN), 'f', 2);

        board.addPiece(Piece.createWhitePiece(Piece.Type.KING), 'f', 1);

        assertEquals(16.5, board.getEvoluationWhitePieces(), 0.001);
        assertEquals(17, board.getEvoluationBlackPieces(), 0.001);

//        assertEquals(19.5, board.getEvoluationWhitePieces(), 0.001);
//        assertEquals(20, board.getEvoluationBlackPieces(), 0.001);
    }

}
