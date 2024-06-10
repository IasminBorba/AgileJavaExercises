package pieces;

import chess.Board;

public class Queen extends Piece {
    public static Type Class = Type.QUEEN;
    private final Board board;

    protected Queen(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Queen create(Color color, Board board) {
        return new Queen(color, board);
    }

    @Override
    public boolean getPossibleMoves(char file, int rank) {
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
                    if (column == z){
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if (aux == x) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z + 1) && aux == x) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z + 1) && aux == x - 1) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((column == z + 1) && aux == x + 1) {
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
                    } else if ((column + aux) - (z + x)  == (column-z)*2){
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if (column + aux == z + x) {
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else if ((z + x) - (column + aux) == (z-column)*2){
                        board.removePiece(this);
                        board.addPiece(this, column, rank);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
