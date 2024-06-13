package pieces;

import chess.Board;

import java.util.ArrayList;

public class Knight extends Piece {
    public static Type Class = Type.KNIGHT;
    private final Board board;

    protected Knight(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Knight create(Color color, Board board) {
        return new Knight(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position){
        ArrayList<String> moves = new ArrayList<>();

        for(int i = 1, j = 2; i < 3; i++, j--){
            moves.add(board.transformPositionString(column+i, rank+j));
            moves.add(board.transformPositionString(column+i, rank-j));
            moves.add(board.transformPositionString(column-i, rank-j));
            moves.add(board.transformPositionString(column-i, rank+j));
        }

        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}