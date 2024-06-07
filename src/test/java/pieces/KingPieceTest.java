package pieces;

import chess.Board;
import chess.Game;

import static pieces.KingPiece.*;

public class KingPieceTest extends PieceTest{
    public void testCreate() {
        Piece kingBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.KING, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createPiece(Color.WHITE,  Class);
        assertEquals(Type.KING, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }

//    public void testKingMove() {
//        Board board = new Board();
//        board.createBoard();
//
//        Piece kingWhite = KingPiece.create(Piece.Color.WHITE);
//        board.addPiece(kingWhite,'d', 4);
//        boolean teste = KingPiece.getPossibleMoves(kingWhite, 'a', 2);
//        assertFalse(KingPiece.getPossibleMoves(kingWhite, 'a', 2);
//        assertTrue(KingPiece.getMoves(kingWhite, 'd', 3));
//
//        assertEquals(1, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(0, board.getPiecesBlack());
//
//
//        Piece kingBlack = KingPiece.create(Piece.Color.BLACK);
//        board.addPiece(kingBlack,'h', 8);
//        assertFalse(KingPiece.getMoves(kingBlack, 'i', 8));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(KingPiece.getMoves(kingBlack, 'h', 7));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(KingPiece.getMoves(kingBlack, 'g', 8));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//    }

    protected Piece createPiece(Color color, Type type) {
        return KingPiece.create(color);
    }
}

