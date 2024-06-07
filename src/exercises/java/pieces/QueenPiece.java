package pieces;

import chess.Board;

public class QueenPiece extends Piece {
    public static Type Class = Type.QUEEN;
    private Board board;

    protected QueenPiece(Color color, Type type) {
        super(color, type);
    }

    public static QueenPiece create(Color color) {
        return new QueenPiece(color, Class);
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
                    if (column == z){
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
                        return true;
                    } else if (aux == x) {
                        board.removePiece(piece);
                        board.addPiece(piece, column, rank);
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
