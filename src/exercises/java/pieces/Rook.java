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
        int column = board.transformPosition2(position).getFirst();
        int rank = board.transformPosition2(position).getLast();

        if (rank == 9 || column == 9) {
            return moves;
        }

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x)) {
                    for (int aux = 0; aux < 8; aux++){
                        if(this != board.getPiece(aux, x)){
                            moves.add(board.transformPositionString(aux, x));
                        } if(this != board.getPiece(z, aux)){
                            moves.add(board.transformPositionString(z, aux));
                        }
                    }
                }
            }
        }
        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}