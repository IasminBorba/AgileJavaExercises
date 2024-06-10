package pieces;

import chess.Board;

public class QueenPiece extends Piece {
    public static Type Class = Type.QUEEN;
    private final Board board;

    protected QueenPiece(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static QueenPiece create(Color color, Board board) {
        return new QueenPiece(color, board);
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
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
