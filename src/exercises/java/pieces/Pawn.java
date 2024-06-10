package pieces;

import chess.Board;

public class Pawn extends Piece {
    public static Type Class = Type.PAWN;
    private final Board board;

    protected Pawn(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Pawn create(Color color, Board board) {
        return new Pawn(color, board);
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

        int aux = rank - 1;
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x + 1)) {
                    if(this.isWhite()) {
                        if (column == z && aux == x + 1) {
                            board.removePiece(this);
                            board.addPiece(this, column, rank);
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (column == z && aux == x - 1) {
                            board.removePiece(this);
                            board.addPiece(this, column, rank);
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }


        return false;
    }
}