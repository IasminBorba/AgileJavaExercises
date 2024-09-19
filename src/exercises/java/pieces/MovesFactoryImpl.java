package pieces;

import util.TransformCoordenate;

import java.util.*;

public class MovesFactoryImpl implements Moves {
    private ArrayList<String> moves = new ArrayList<>();
    private final Piece piece;
    private final int pieceRank;
    private final int pieceColumn;

    public MovesFactoryImpl(Piece piece) {
        this.piece = piece;
        this.pieceRank = piece.getPosition().getRank();
        this.pieceColumn = piece.getPosition().getColumn();
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
        for (int columnOffset = columnDirection, rankUp = pieceRank + 1, rankDown = pieceRank - 1; isValidColumn(pieceColumn + columnOffset); columnOffset += columnDirection, rankUp++, rankDown--)
            addMovementsDifferentRanks(rankUp, rankDown, columnOffset);
    }

    private boolean isValidColumn(int col) {
        return col >= 0 && col <= 8;
    }

    private void rookMoves() {
        straightRankMovement();
        removeInvalidMoves();
    }

    private void straightRankMovement() {
        for (int coordinate = 0; coordinate < 8; coordinate++) {
            moves.add(TransformCoordenate.convertCoordinate(coordinate, pieceRank));
            moves.add(TransformCoordenate.convertCoordinate(pieceColumn, coordinate));
        }
    }

    private void knightMoves() {
        for (int movimentColumn = -2; movimentColumn <= 2; movimentColumn++) {
            if (movimentColumn == 0) continue;

            int absoluteNumber = Math.abs(movimentColumn);
            int movimentRank = 3 - absoluteNumber;

            addMovementsDifferentRanks(pieceRank + movimentRank, pieceRank - movimentRank, movimentColumn);
        }
        removeInvalidMoves();
    }

    private void addMovementsDifferentRanks(int rankUp, int rankDown, int columnOffset) {
        moves.add(TransformCoordenate.convertCoordinate(pieceColumn + columnOffset, rankUp));
        moves.add(TransformCoordenate.convertCoordinate(pieceColumn + columnOffset, rankDown));
    }

    private void kingMoves() {
        for (int initialColumn = pieceColumn - 1; initialColumn <= pieceColumn + 1; initialColumn++)
            for (int initialRank = pieceRank - 1; initialRank <= pieceRank + 1; initialRank++)
                moves.add(TransformCoordenate.convertCoordinate(initialColumn, initialRank));

        removeInvalidMoves();
    }

    private void pawnMoves() {
        if (piece.isWhite())
            moves.add(TransformCoordenate.convertCoordinate(pieceColumn, pieceRank + 1));
        else
            moves.add(TransformCoordenate.convertCoordinate(pieceColumn, pieceRank - 1));

        removeInvalidMoves();
    }

    private void removeInvalidMoves() {
        moves.removeIf(String::isEmpty);
        moves.removeIf(move -> move.contains(TransformCoordenate.convertCoordinate(pieceColumn, pieceRank)));
        moves = new ArrayList<>(new LinkedHashSet<>(moves));
    }
}