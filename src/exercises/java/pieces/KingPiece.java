package pieces;

import chess.Board;

public class KingPiece extends Piece {
    public static Type Class = Type.KING;
    private final Board board;
    public boolean allow;

    protected KingPiece(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static KingPiece create(Color color, Board board) {
        return new KingPiece(color, board);
    }

    @Override
    public boolean getPossibleMoves(char file, int rank){
        boolean permission = false;
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
                    if ((column == 1 + z) && (aux == x)) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == 1 + z) && aux == x - 1) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == 1 + z) && aux == x + 1) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x - 1) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x + 1) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z) && (aux == x + 1)) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z) && (aux == x - 1)) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        allow = permission;
        return permission;
    }
}
