package pieces;

import chess.Board;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;
    private final Board board;

    protected Bishop(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Bishop create(Color color, Board board) {
        return new Bishop(color, board);
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
                    System.out.println();
                    for (int auxColumn = z-1, auxRankUp = x+1, auxRankDown = x-1; auxColumn >= 0; auxColumn--, auxRankDown--, auxRankUp++) {
                        if(auxRankUp < 8){
                            moves.add(board.transformPositionString(auxColumn, auxRankUp));
                        }
                        if(auxRankUp >= 0){
                            moves.add(board.transformPositionString(auxColumn, auxRankDown));
                        }
                    }

                    for (int auxColumn = z+1, auxRankUp = x+1, auxRankDown = x-1; auxColumn < 8; auxColumn++, auxRankDown--, auxRankUp++) {
                        if(auxRankUp < 8){
                            moves.add(board.transformPositionString(auxColumn, auxRankUp));
                        }
                        if(auxRankUp >= 0){
                            moves.add(board.transformPositionString(auxColumn, auxRankDown));
                        }
                    }
                }
            }
        }
        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}
