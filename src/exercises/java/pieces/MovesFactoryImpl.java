package pieces;

import chess.Board;

import java.util.ArrayList;

public class MovesFactoryImpl implements Moves {
    ArrayList<String> moves = new ArrayList<>();
    Piece piece;
    Board board;

    public MovesFactoryImpl(Piece piece, Board board) {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList<String> possibleMoves() {
        switch (piece.getType()) {
            case BISHOP -> bishopMoves(piece, board);
//            case KING -> kingMoves();
//            case KNIGHT -> knightMoves();
//            case PAWN -> pawnMoves();
//            case QUEEN -> queenMoves();
//            case ROOK -> rookMoves();
        }

        return moves;
    }

    private void bishopMoves(Piece piece, Board board) {
        possibleMovementDiagonals();
        moves.removeIf(move -> move.contains("error"));
    }

//    private void queenMoves() {
//        possibleMovementDiagonals();
//
//        moves.removeIf(move -> move.contains("error"));
//    }


    private void possibleMovementDiagonals() {
        movesDiagonals(1);
        movesDiagonals(-1);
    }


    private void movesDiagonals(int columnDirection) {
        for (int columnOffset = columnDirection, rankUp = piece.rank + 1, rankDown = piece.rank - 1; isValidColumn(piece.column + columnOffset); columnOffset += columnDirection, rankUp++, rankDown--)
            addMoves(rankUp, rankDown, columnOffset);
    }

    private boolean isValidColumn(int col) {
        return col >= 0 && col <= 8;
    }

    private void addMoves(int rankUp, int rankDown, int columnOffset) {
        moves.add(board.transformPositionString(piece.column + columnOffset, rankUp));
        moves.add(board.transformPositionString(piece.column + columnOffset, rankDown));
    }
}
