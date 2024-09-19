package chess;

import pieces.Piece;
import util.CoordinateTransformer;

import java.util.*;

public class MovesFactoryImpl implements Moves {
    private ArrayList<String> moves = new ArrayList<>();
    private final Piece piece;
    private final int pieceRow;
    private final int pieceFile;

    public MovesFactoryImpl(Piece piece) {
        this.piece = piece;
        this.pieceRow = piece.getPosition().getRow();
        this.pieceFile = piece.getPosition().getFile();
    }

    public ArrayList<String> possibleMoves() {
        switch (piece.getType()) {
            case BISHOP -> bishopPossibleMoves();
            case QUEEN -> queenPossibleMoves();
            case ROOK -> rookPossibleMoves();
            case KNIGHT -> knightPossibleMoves();
            case KING -> kingPossibleMoves();
            case PAWN -> pawnPossibleMoves();
        }
        return moves;
    }

    private void bishopPossibleMoves() {
        possibleMovementDiagonals();
        removeInvalidPossibleMoves();
    }

    private void queenPossibleMoves() {
        possibleMovementDiagonals();
        straightRowMovement();
        removeInvalidPossibleMoves();
    }

    private void possibleMovementDiagonals() {
        getMovesDiagonals(1);
        getMovesDiagonals(-1);
    }

    private void getMovesDiagonals(int fileDirection) {
        for (int fileOffset = fileDirection, rowUp = pieceRow + 1, rowDown = pieceRow - 1; isValidFile(pieceFile + fileOffset); fileOffset += fileDirection, rowUp++, rowDown--)
            addMovementsDifferentRows(rowUp, rowDown, fileOffset);
    }

    private boolean isValidFile(int col) {
        return col >= 0 && col <= 8;
    }

    private void rookPossibleMoves() {
        straightRowMovement();
        removeInvalidPossibleMoves();
    }

    private void straightRowMovement() {
        for (int coordinate = 0; coordinate < 8; coordinate++) {
            moves.add(CoordinateTransformer.positionToCoordinateString(coordinate, pieceRow));
            moves.add(CoordinateTransformer.positionToCoordinateString(pieceFile, coordinate));
        }
    }

    private void knightPossibleMoves() {
        for (int movimentFile = -2; movimentFile <= 2; movimentFile++) {
            if (movimentFile == 0) continue;

            int absoluteNumber = Math.abs(movimentFile);
            int movimentRow = 3 - absoluteNumber;

            addMovementsDifferentRows(pieceRow + movimentRow, pieceRow - movimentRow, movimentFile);
        }
        removeInvalidPossibleMoves();
    }

    private void addMovementsDifferentRows(int rowUp, int rowDown, int fileOffset) {
        moves.add(CoordinateTransformer.positionToCoordinateString(pieceFile + fileOffset, rowUp));
        moves.add(CoordinateTransformer.positionToCoordinateString(pieceFile + fileOffset, rowDown));
    }

    private void kingPossibleMoves() {
        for (int initialFile = pieceFile - 1; initialFile <= pieceFile + 1; initialFile++)
            for (int initialRow = pieceRow - 1; initialRow <= pieceRow + 1; initialRow++)
                moves.add(CoordinateTransformer.positionToCoordinateString(initialFile, initialRow));

        removeInvalidPossibleMoves();
    }

    private void pawnPossibleMoves() {
        if (piece.isWhite())
            moves.add(CoordinateTransformer.positionToCoordinateString(pieceFile, pieceRow + 1));
        else
            moves.add(CoordinateTransformer.positionToCoordinateString(pieceFile, pieceRow - 1));

        removeInvalidPossibleMoves();
    }

    private void removeInvalidPossibleMoves() {
        moves.removeIf(String::isEmpty);
        moves.removeIf(move -> move.contains(CoordinateTransformer.positionToCoordinateString(pieceFile, pieceRow)));
        moves = new ArrayList<>(new LinkedHashSet<>(moves));
    }
}