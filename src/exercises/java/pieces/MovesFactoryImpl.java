package pieces;

import java.util.*;

public class MovesFactoryImpl implements Moves {
    ArrayList<String> moves = new ArrayList<>();
    Piece piece;

    public MovesFactoryImpl(Piece piece) {
        this.piece = piece;
    }

    public ArrayList<String> possibleMoves() {
        switch (piece.getType()) {
            case BISHOP -> bishopMoves();
            case QUEEN -> queenMoves();
            case ROOK -> rookMoves();
            case KNIGHT -> knightMoves();
            case KING -> kingMoves();
            case PAWN -> pawnMoves();
        }
        return moves;
    }

    private void bishopMoves() {
        possibleMovementDiagonals();
        removeInvalidMoves();
    }

    private void queenMoves() {
        possibleMovementDiagonals();
        straightRankMovement();
        removeInvalidMoves();
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

    private void rookMoves() {
        straightRankMovement();
        removeInvalidMoves();
    }

    private void straightRankMovement() {
        for (int initialColumn = 0; initialColumn < 8; initialColumn++)
            moves.add(transformPositionCoordinate(initialColumn, piece.rank));

        for (int initialRank = 0; initialRank < 8; initialRank++)
            moves.add(transformPositionCoordinate(piece.column, initialRank));
    }

    private void knightMoves() {
        for (int movimentColumn = -2; movimentColumn <= 2; movimentColumn++) {
            if (movimentColumn == 0) continue;

            int movimentRank = applyRule(movimentColumn);
            addMoves(piece.rank + movimentRank, piece.rank - movimentRank, movimentColumn);
        }
        removeInvalidMoves();
    }

    private int applyRule(int movimentColumn) {
        int absoluteNumber = Math.abs(movimentColumn);
        return 3 - absoluteNumber;
    }

    private void addMoves(int rankUp, int rankDown, int columnOffset) {
        moves.add(transformPositionCoordinate(piece.column + columnOffset, rankUp));
        moves.add(transformPositionCoordinate(piece.column + columnOffset, rankDown));
    }

    private void kingMoves() {
        for (int initialColumn = piece.column - 1; initialColumn <= piece.column + 1; initialColumn++)
            for (int initialRank = piece.rank - 1; initialRank <= piece.rank + 1; initialRank++)
                moves.add(transformPositionCoordinate(initialColumn, initialRank));

        removeInvalidMoves();
    }

    private void pawnMoves() {
        if (piece.isWhite())
            moves.add(transformPositionCoordinate(piece.column, piece.rank + 1));
        else
            moves.add(transformPositionCoordinate(piece.column, piece.rank - 1));

        removeInvalidMoves();
    }

    private void removeInvalidMoves() {
        moves.removeIf(move -> move.contains(transformPositionCoordinate(piece.column, piece.rank)));
        moves.removeIf(move -> move.contains("error"));
        moves = new ArrayList<>(new LinkedHashSet<>(moves));
    }

    public String transformPositionCoordinate(int column, int rank) {
        StringBuilder coordinate = new StringBuilder();
        coordinate.append(positionColumn(column));
        coordinate.append(positionRank(rank));

        return coordinate.toString();
    }

    private String positionColumn(int column) {
        return switch (column) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "error";
        };
    }

    private String positionRank(int rank) {
        rank++;
        if (rank >= 0 && rank <= 8) {
            return String.valueOf(rank);
        } else
            return "error";
    }
}