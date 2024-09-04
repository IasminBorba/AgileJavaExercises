package pieces;

import chess.Board;

import java.util.*;

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
            case BISHOP -> bishopMoves();
            case QUEEN -> queenMoves();
            case KNIGHT -> knightMoves();
            case KING -> kingMoves();
            case PAWN -> pawnMoves();
            case ROOK -> straightRankMovement();
        }
        removeInvalidMoves();
        return moves;
    }

    private void bishopMoves() {
        possibleMovementDiagonals();
    }

    private void queenMoves() {
        possibleMovementDiagonals();
        straightRankMovement();
    }

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

    private void knightMoves() {
        for (int movimentColumn = -2; movimentColumn <= 2; movimentColumn++) {
            if (movimentColumn == 0) continue;

            int movimentRank = applyRule(movimentColumn);
            addMoves( piece.rank + movimentRank, piece.rank - movimentRank, movimentColumn);
        }
    }

    private int applyRule(int movimentColumn) {
        int absoluteNumber = Math.abs(movimentColumn);
        return 3 - absoluteNumber;
    }

    private void addMoves(int rankUp, int rankDown, int columnOffset) {
        moves.add(board.transformPositionString(piece.column + columnOffset, rankUp));
        moves.add(board.transformPositionString(piece.column + columnOffset, rankDown));
    }

    private void kingMoves() {
        for(int initialColumn = piece.column-1; initialColumn <= piece.column+1; initialColumn++)
            for(int initialRank = piece.rank-1; initialRank <= piece.rank+1; initialRank++)
                moves.add(board.transformPositionString(initialColumn, initialRank));
    }

    private void pawnMoves() {
        if (piece.isWhite())
            moves.add(board.transformPositionString(piece.column, piece.rank + 1));
        else
            moves.add(board.transformPositionString(piece.column, piece.rank - 1));
    }

    private void straightRankMovement() {
        for (int initialColumn = 0; initialColumn < 8; initialColumn++)
            moves.add(board.transformPositionString(initialColumn, piece.rank));

        for (int initialRank = 0; initialRank < 8; initialRank++)
            moves.add(board.transformPositionString(piece.column, initialRank));
    }

    private void removeInvalidMoves() {
        moves.removeIf(move -> move.contains(board.transformPositionString(piece.column, piece.rank)));
        moves.removeIf(move -> move.contains("error"));

        moves = new ArrayList<>(new LinkedHashSet<>(moves));
    }
}
