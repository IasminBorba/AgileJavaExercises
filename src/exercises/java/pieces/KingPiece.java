package pieces;

import chess.Board;

public class KingPiece extends Piece {
    public static Type Class = Type.KING;
    private Board board;
    public boolean allow;

    protected KingPiece(Color color, Type type) {
        super(color, type);
    }

    public static KingPiece create(Color color) {
        return new KingPiece(color, Class);
    }

    @Override
    public boolean getPossibleMoves(Piece piece, int column, int rank){
        boolean permission = false;
        if (rank > 8){
            return false;
        }

        if (column == 9) {
            return false;
        }

        int aux = rank - 1;
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (piece == board.getPiece(z, x + 1)) {
                    if ((column == 1 + z) && (aux == x)) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == 1 + z) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == 1 + z) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == z - 1) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == z) && (aux == x + 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if ((column == z) && (aux == x - 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
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

    public boolean getMoves(Piece piece, int column, int rank){
        return getPossibleMoves(piece, column, rank);
    }
}
