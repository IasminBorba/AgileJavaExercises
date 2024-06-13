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

        moves.add(board.transformPositionString(column,rank-1));
        moves.add(board.transformPositionString(column,rank+1));
        for(int aux = -1; aux < 2; aux++){
            moves.add(board.transformPositionString(column+1,rank+aux));
            moves.add(board.transformPositionString(column-1,rank+aux));
        }

        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}
