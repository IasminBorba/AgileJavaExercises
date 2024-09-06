package chess;

import pieces.*;
import pieces.Piece.*;

import java.util.concurrent.atomic.AtomicReference;

public class Game {
    private final Board board;
    public double strengthWhite = 0;
    public double strengthBlack = 0;

    public Game(Board board) {
        this.board = board;
    }

    double getStrengthBlackPiece() {
        alterPawnForceSameColumn();
        calculateStrength();
        return strengthBlack;
    }

    double getStrengthWhitePiece() {
        alterPawnForceSameColumn();
        calculateStrength();
        return strengthWhite;
    }

    public void alterPawnForceSameColumn() {
        Piece[][] boardArray = board.getBoard();

        for (int column = 0; column < 8; column++)
            if (hasDuplicatePawnsInColumn(boardArray, column))
                adjustPawnStrengthInColumn(boardArray, column);
    }

    private boolean hasDuplicatePawnsInColumn(Piece[][] boardArray, int column) {
        for (int rank = 0; rank < 8; rank++) {
            Piece piece = boardArray[column][rank];
            if (piece != null && piece.getType() == Type.PAWN) {
                for (int checkRow = rank + 1; checkRow < 8; checkRow++) {
                    Piece otherPiece = boardArray[column][checkRow];
                    if (otherPiece != null && otherPiece.getType() == Type.PAWN && otherPiece.getRepresentation() == piece.getRepresentation())
                        return true;
                }
            }
        }
        return false;
    }

    private void adjustPawnStrengthInColumn(Piece[][] boardArray, int column) {
        for (int rank = 0; rank < 8; rank++) {
            Piece piece = boardArray[column][rank];
            if (piece != null && piece.getType() == Type.PAWN && piece.getPoints() == 1)
                piece.setPoints(0.5);
        }
    }

    public void calculateStrength() {
        strengthWhite = calculateStrengthForColor(Color.WHITE);
        strengthBlack = calculateStrengthForColor(Color.BLACK);
    }

    private double calculateStrengthForColor(Color color) {
        AtomicReference<Double> strength = new AtomicReference<>((double) 0);
        board.iterateBoard((rank, column) -> {
            Piece currentPiece = board.getPiece(column, rank);
            if (currentPiece != null && currentPiece.getColor() == color) {
                strength.updateAndGet(v -> v + currentPiece.getPoints());
            }
        });
        return strength.get();
    }
}
