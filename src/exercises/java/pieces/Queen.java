package pieces;

import chess.Board;

import java.util.ArrayList;

public class Queen extends Piece {
    public static Type Class = Type.QUEEN;
    private final Board board;

    protected Queen(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Queen create(Color color, Board board) {
        return new Queen(color, board);
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
