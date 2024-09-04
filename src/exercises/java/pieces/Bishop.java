package pieces;

import chess.Board;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;
    private final Board board;
    ArrayList<String> moves = new ArrayList<>();

    protected Bishop(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Bishop create(Color color, Board board) {
        return new Bishop(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this, board);
        return movesFactory.possibleMoves();
    }

//    @Override
//    public ArrayList<String> getPossibleMoves(String position) {
//        updatePossibleMoves();
//        return moves;
//    }
//
//    private void updatePossibleMoves() {
//        movesRightDiagonals();
//        movesLeftDiagonals();
//        moves.removeIf(move -> move.contains("error"));
//    }
//
//    private void movesRightDiagonals() {
//        movesDiagonals(1);
//    }
//
//    private void movesLeftDiagonals() {
//        movesDiagonals(-1);
//    }
//
//    private void movesDiagonals(int columnDirection) {
//        for (int columnOffset = columnDirection, rankUp = rank + 1, rankDown = rank - 1; isValidColumn(column + columnOffset); columnOffset += columnDirection, rankUp++, rankDown--)
//            addMoves(rankUp, rankDown, columnOffset);
//    }
//
//    private boolean isValidColumn(int col) {
//        return col >= 0 && col <= 8;
//    }
//
//    private void addMoves(int rankUp, int rankDown, int columnOffset) {
//        moves.add(board.transformPositionString(column + columnOffset, rankUp));
//        moves.add(board.transformPositionString(column + columnOffset, rankDown));
//    }
}