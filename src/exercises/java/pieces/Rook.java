package pieces;

import chess.Board;

public class Rook extends Piece {
    public static Type Class = Type.ROOK;
    private final Board board;

    protected Rook(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Rook create(Color color, Board board) {
        return new Rook(color, board);
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

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x + 1)) {
                    if (column == z){
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if (rank - 1 == x) {
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