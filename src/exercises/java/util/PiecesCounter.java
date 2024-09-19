package util;

import chess.Board;
import pieces.Piece;

public class PiecesCounter {
    private final Board board;
    private int piecesWhite;
    private int piecesBlack;

    public PiecesCounter(Board board) {
        this.board = board;
        updateQuantityOfPieces();
    }

    private void updateQuantityOfPieces() {
        for (Piece piece : board.getPiecesOnBoard())
            if (piece.isWhite())
                piecesWhite++;
            else
                piecesBlack++;
    }

    public int counter() {
        return board.getPiecesOnBoard().size();
    }

    public int whiteCount() {
        return piecesWhite;
    }

    public int blackCount() {
        return piecesBlack;
    }
}
