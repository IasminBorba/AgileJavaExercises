package pieces;

import chess.Board;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;
    private final Board board;

    protected Bishop(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Bishop create(Color color, Board board) {
        return new Bishop(color, board);
    }

    @Override
    public boolean getPossibleMoves(char file, int rank){
        if (rank > 8){
            return false;
        }
        int column = Board.transformPosition(file);

        if (column == 9) {
            return false;
        }
        int aux = rank-1;

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x + 1)) {
                    if ((column + aux) - (z + x)  == (column-z)*2){
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if (column + aux == z + x) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((z + x) - (column + aux) == (z-column)*2) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
