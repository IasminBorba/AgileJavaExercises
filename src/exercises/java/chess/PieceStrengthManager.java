package chess;

import pieces.*;
import pieces.Piece.*;

import java.util.concurrent.atomic.AtomicReference;

public class PieceStrengthManager {
    public double totalWhiteStrength;
    public double totalBlackStrength;
    private final Board boardCells;

    public PieceStrengthManager(Board boardCells) {
        this.boardCells = boardCells;
    }

    public double calculateBlackPieceStrength() {
        adjustPawnStrengthInSamefile();
        calculateTotalStrength();
        return totalBlackStrength;
    }

    public double calculateWhitePieceStrength() {
        adjustPawnStrengthInSamefile();
        calculateTotalStrength();
        return totalWhiteStrength;
    }

    protected void adjustPawnStrengthInSamefile() {
        Piece[][] boardCellsAux = boardCells.getBoardCells();

        for (int file = 0; file < 8; file++)
            if (hasDuplicatePawnsInFile(boardCellsAux, file))
                adjustPawnStrengthInFile(boardCellsAux, file);
    }

    protected boolean hasDuplicatePawnsInFile(Piece[][] boardCells, int file) {
        for (int row = 0; row < 8; row++) {
            Piece piece = boardCells[file][row];
            if (piece != null && piece.getType() == Type.PAWN) {
                for (int checkRow = row + 1; checkRow < 8; checkRow++) {
                    Piece otherPiece = boardCells[file][checkRow];
                    if (otherPiece != null && otherPiece.getType() == Type.PAWN && otherPiece.getRepresentation() == piece.getRepresentation())
                        return true;
                }
            }
        }
        return false;
    }

    protected void adjustPawnStrengthInFile(Piece[][] boardCells, int file) {
        for (int row = 0; row < 8; row++) {
            Piece piece = boardCells[file][row];
            if (piece != null && piece.getType() == Type.PAWN && piece.getPoints() == 1)
                piece.updatePoints(0.5);
        }
    }

    public void calculateTotalStrength() {
        totalWhiteStrength = calculateStrengthByColor(Color.WHITE);
        totalBlackStrength = calculateStrengthByColor(Color.BLACK);
    }

    protected double calculateStrengthByColor(Color color) {
        AtomicReference<Double> strength = new AtomicReference<>((double) 0);
        boardCells.iterateBoard((row, file) -> {
            Piece currentPiece = boardCells.getPiece(file, row);
            if (currentPiece != null && currentPiece.getColor() == color)
                strength.updateAndGet(v -> v + currentPiece.getPoints());
        });
        return strength.get();
    }
}
