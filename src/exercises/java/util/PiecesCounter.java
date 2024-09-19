package util;

import chess.Board;
import pieces.Piece;

public class PiecesCounter {
    private final Board board;
    private int totalWhite;
    private int totalBlack;

    public PiecesCounter(Board board) {
        this.board = board;
        updateQuantityOfPieces();
    }

    private void updateQuantityOfPieces() {
        for (Piece piece : board.getPiecesOnBoard())
            if (piece.isWhite())
                totalWhite++;
            else
                totalBlack++;
    }

    public int counter() {
        return board.getPiecesOnBoard().size();
    }

    public int whiteCount() {
        return totalWhite;
    }

    public int blackCount() {
        return totalBlack;
    }
}
