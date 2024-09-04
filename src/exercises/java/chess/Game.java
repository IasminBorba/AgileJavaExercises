package chess;

import pieces.Piece;


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
        for (int row = 0; row < 8; row++) {
            Piece piece = boardArray[column][row];
            if (piece != null && piece.getType() == Piece.Type.PAWN) {
                for (int checkRow = row + 1; checkRow < 8; checkRow++) {
                    Piece otherPiece = boardArray[column][checkRow];
                    if (otherPiece != null && otherPiece.getType() == Piece.Type.PAWN && otherPiece.getRepresentation() == piece.getRepresentation())
                        return true;
                }
            }
        }
        return false;
    }

    private void adjustPawnStrengthInColumn(Piece[][] boardArray, int column) {
        for (int row = 0; row < 8; row++) {
            Piece piece = boardArray[column][row];
            if (piece != null && piece.getType() == Piece.Type.PAWN && piece.getPoints() == 1)
                piece.setPoints(0.5);
        }
    }


    public void calculateStrength() {
        strengthWhite = calculateStrengthForColor(true);
        strengthBlack = calculateStrengthForColor(false);
    }

    private double calculateStrengthForColor(boolean isWhite) {
        double strength = 0;
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Piece currentPiece = board.getPiece(z, x);
                if (currentPiece != null && currentPiece.isWhite() == isWhite)
                    strength += currentPiece.getPoints();
            }
        }
        return strength;
    }
}
