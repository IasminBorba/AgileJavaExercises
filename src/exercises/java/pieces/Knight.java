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
        int column = board.transformPosition2(position).getFirst();
        int rank = board.transformPosition2(position).getLast();

        if (rank == 9 || column == 9) {
            return moves;
        }

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (this == board.getPiece(z, x + 1)) {
//                    if (column == z + 1 && (aux == x + 2 || aux == x - 2)){
                        moves.add(board.transformPositionString(z+1, x+3));
                        moves.add(board.transformPositionString(z+1, x-1));
//                    } else if (column == z + 2 && (aux == x + 1 || aux == x - 1)){
                    moves.add(board.transformPositionString(z+2, x+2));
                    moves.add(board.transformPositionString(z+2, x));
//                    } else if (column == z - 1 && (aux == x + 2 || aux == x - 2)){
                    moves.add(board.transformPositionString(z-1, x+3));
                    moves.add(board.transformPositionString(z-1, x-1));
//                    } else if (column == z - 2 && (aux == x + 1 || aux == x - 1)){
                    moves.add(board.transformPositionString(z-2, x+2));
                    moves.add(board.transformPositionString(z-2, x));
                }
            }
        }
        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}