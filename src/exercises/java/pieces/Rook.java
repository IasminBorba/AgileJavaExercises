package pieces;

import chess.Board;

import java.util.ArrayList;

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
    public ArrayList<String> getPossibleMoves(String position){
        ArrayList<String> moves = new ArrayList<>();

        for (int aux = 0; aux < 8; aux++){
            if(this != board.getPiece(aux, rank)){
                moves.add(board.transformPositionString(aux, rank));
            } if(this != board.getPiece(column, aux)){
                moves.add(board.transformPositionString(column, aux));
            }
        }

        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}