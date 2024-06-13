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

        for (int aux = 0; aux < 8; aux++){
            if(this != board.getPiece(aux, rank)){
                moves.add(board.transformPositionString(aux, rank));
            } if(this != board.getPiece(column, aux)){
                moves.add(board.transformPositionString(column, aux));
            }
        }

        for (int auxColumn = column-1, auxRankUp = rank+1, auxRankDown = rank-1; auxColumn >= 0; auxColumn--, auxRankDown--, auxRankUp++) {
            if(auxRankUp < 8){
                moves.add(board.transformPositionString(auxColumn, auxRankUp));
            }
            if(auxRankUp >= 0){
                moves.add(board.transformPositionString(auxColumn, auxRankDown));
            }
        }

        for (int auxColumn = column+1, auxRankUp = rank+1, auxRankDown = rank-1; auxColumn < 8; auxColumn++, auxRankDown--, auxRankUp++) {
            if(auxRankUp < 8){
                moves.add(board.transformPositionString(auxColumn, auxRankUp));
            }
            if(auxRankUp >= 0){
                moves.add(board.transformPositionString(auxColumn, auxRankDown));
            }
        }

        moves.removeIf(move -> move.contains("error"));
        return moves;
    }
}
