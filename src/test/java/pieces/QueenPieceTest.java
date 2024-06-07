package pieces;

import static pieces.QueenPiece.*;

public class QueenPieceTest extends PieceTest{
    public void testCreate() {
        Piece queenBlack = createPiece(Color.BLACK, Class);
        assertEquals(Type.QUEEN, queenBlack.getType());
        assertEquals('Q', queenBlack.getRepresentation());

        Piece queenWhite = createPiece(Color.WHITE, Class);
        assertEquals(Type.QUEEN, queenWhite.getType());
        assertEquals('q', queenWhite.getRepresentation());
    }

//    public void testQueenMove() {
//        board.createBoard();
//
//        Piece queenWhite = QueenPiece.create(Piece.Color.WHITE);
//        board.addPiece(queenWhite,'a', 8);
//        assertFalse(QueenPiece.getMoves(queenWhite, 'a', 9));
//        assertFalse(QueenPiece.getMoves(queenWhite, 'i', 8));
//        assertFalse(QueenPiece.getMoves(queenWhite, 'b', 7));
//        assertTrue(QueenPiece.getMoves(queenWhite, 'a', 7));
//
//        assertEquals(1, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(0, board.getPiecesBlack());
//
//
//        Piece queenBlack = Piece.createBlackPiece(Piece.Type.QUEEN);
//        board.addPiece(queenBlack,'f', 5);
//        assertFalse(QueenPiece.getMoves(queenBlack, 'f', 9));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertTrue(QueenPiece.getMoves(queenBlack, 'c', 5));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//
//
//        assertFalse(QueenPiece.getMoves(queenBlack, 'f', 2));
//        assertTrue(QueenPiece.getMoves(queenBlack, 'c', 2));
//        assertEquals(2, board.pieceCount());
//        assertEquals(1, board.getPiecesWhite());
//        assertEquals(1, board.getPiecesBlack());
//    }


    protected Piece createPiece(Color color, Type type) {
        return QueenPiece.create(color);
    }
}

