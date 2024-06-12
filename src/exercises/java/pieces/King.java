package pieces;

import chess.Board;

import java.util.ArrayList;

public class King extends Piece {
    public static Type Class = Type.KING;
    private final Board board;

    protected King(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static King create(Color color, Board board) {
        return new King(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        ArrayList<String> moves = new ArrayList<>();
        int column = board.transformPosition2(position).getFirst();
        int rank = board.transformPosition2(position).getLast();

        if (rank == 9 || column == 9) {
            return moves;
        }

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x + 1)) {
                    moves.add(board.transformPositionString(z+1,x));
                    moves.add(board.transformPositionString(z+1,x+1));
                    moves.add(board.transformPositionString(z+1,x+2));
                    moves.add(board.transformPositionString(z-1,x));
                    moves.add(board.transformPositionString(z-1,x+1));
                    moves.add(board.transformPositionString(z-1,x+2));
                    moves.add(board.transformPositionString(z,x+2));
                    moves.add(board.transformPositionString(z,x));
                }
            }
        }
        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}
