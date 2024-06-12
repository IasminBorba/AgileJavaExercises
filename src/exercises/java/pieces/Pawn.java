package pieces;

import chess.Board;

import java.util.ArrayList;

public class Pawn extends Piece {
    public static Type Class = Type.PAWN;
    private final Board board;

    protected Pawn(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Pawn create(Color color, Board board) {
        return new Pawn(color, board);
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
                if (this == board.getPiece(z, x+1)) {
                    if (x < 7){
                        if(this.isWhite()) {
                            moves.add(board.transformPositionString(z,x+2));
                        } else {
                            moves.add(board.transformPositionString(z, x));
                        }
                    }
                }
            }
        }

        return moves;
    }
}