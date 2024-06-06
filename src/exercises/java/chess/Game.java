package chess;

import pieces.Piece;


public class Game {
    private final Board board;
    public double strengthWhite = 0;
    public double strengthBlack = 0;

    public Game(Board board) {
        this.board = board;
    }

    public void calculateStrength(double strengthWhite, double strengthBlack) {
        double auxWhiteStrength = 0;
        double auxBlackStrength = 0;

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Piece auxPiece = board.getPiece(z, x + 1);
                if (auxPiece.getType() != Piece.Type.NO_PIECE) {
                    if (auxPiece.isWhite()) {
                        auxWhiteStrength += auxPiece.getPoints();
                    } else {
                        auxBlackStrength += auxPiece.getPoints();
                    }
                }
            }
        }

        strengthWhite = auxWhiteStrength;
        strengthBlack = auxBlackStrength;
    }

    public boolean newKingPosition(Piece piece, char files, int rank) {
        boolean permission = false;
        int aux = rank - 1;
        int file = board.transformPosition(files);
        if (file == 9) {
            return false;
        }

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (piece == board.getPiece(z, x + 1)) {
                    if ((file == 1 + z) && (aux == x)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == 1 + z) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == 1 + z) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z) && (aux == x + 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z) && (aux == x - 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    }
                }
            }
        }
        return permission;
    }

    public void alterPawnForceSameColumn(int column, int rank) {
        boolean sameColumnPawn = false;
        for (int i = 0; i < 8; i++) {
            if (i != rank && board.getPiece(column, i + 1).getRepresentation() == board.getPiece(column, rank).getRepresentation()) {
                sameColumnPawn = true;
                Piece auxPiece = board.getPiece(column, i + 1);
                if (auxPiece.getPoints() == 1) {
                    auxPiece.setPoints(0.5);
                }
            }
        }
        if (sameColumnPawn) {
            Piece auxPiece = board.getPiece(column, rank);
            if (auxPiece.getPoints() == 1) {
                auxPiece.setPoints(0.5);
            }
        }
    }

    double getStrengthBlackPiece(){
//        alterPawnForceSameColumn();
//        calculateStrength();
        return strengthBlack;
    }
    double getStrengthWhitePiece(){
//        alterPawnForceSameColumn();
//        calculateStrength();
        return strengthWhite;
    }
}
